## NormalSQL

Transform normal SQL statements into typesafe PreparedStatement and ResultSet wrappers.

No need for mappings, annotations, DSLs, or string templates. 

NormalSQL understands your SQL as-is. It finds all query parameters and result columns. 
Which are then used to code generate wrappers. 

### Simple Example

This [SQL](doc/example/SimpleExample.sql):

```sql
SELECT first, last, age FROM people WHERE age > 18
```
Generates the [PreparedStatement](doc/example/SelectPeopleOlderThan.java) and [ResultSet](doc/example/SelectPeopleOlderThanResultSet.java) (wrappers):
```java
// pseudo-code
class SimpleSelect 
    int ageGT = 18
    SimpleSelectResultSet execute()
        
class SimpleSelectResultSet 
    boolean next()
    String first
    String last
    int age
```

Here's how your app will use these wrappers:

```java
// pseudo-code
try
( 
    conn = DriverManager.getConnection( ... )
    select = new SimpleSelect( conn )
) 
{
    select.ageGT = 21 
    results = select.execute()

    while( results.next() ) 
    {
        println( results )
    }
}
```

## Usage

NormalSQL requires a live running instance of the target database(s) to gather metadata.

### Petclinic (Inspired) Demo

The [normalsql-demo]() project shows a typical CRUD implemenation. (The NormalSQL generated wrappers are included.)

### Maven Plugin

```xml
<plugin>
  <groupId>org.normalsql</groupId>
  <artifactId>normalsql</artifactId>
  <version>0.0.1</version>
  <executions>
    <execution>
      <goals>
        <goal>normalsql</goal>
      </goals>
    </execution>
  </executions>
  <configuration>
    <url>jdbc:h2:tcp://localhost/petclinic</url>
    <username>username</username>
    <password>password</password>
  </configuration>
</plugin>
```
NormalSQL's plugin is bound to Maven's `generate-sources` phase, therefore runs before `compile` [[1]](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html) [[2]](https://maven.apache.org/guides/mini/guide-generating-sources.html). So normally you'd do full rebuilds like this:
```shell
$ mvn clean compile
```
Or just re-run NormalSQL as needed:
```shell
$ mvn normalsql:normalsql
```

### Command Line

Details go here.

### IntelliJ Extension

(Future work.)

# SQL Support

NormalSQL recognizes DML statements from SQL-92 thru SQL-2016. This includes CTEs, JOINs, UNIONs, aggregate functions, and so forth.

(List of sample SQLs use for testing goes here.)



### Benefits

| Feature                 | Detail                                                                                                                                                                               |
|-------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| SQL-first workflow      | Use normal SQL to generate app source code. Instead of using mappings, DSLs, or templates to generate SQL.                                                                           |
| Plaintext SQL files     | &bull; Use your favorite SQL client (eg DBeaver, DataGrip). <br/> &bull; Devs & DBAs can now work independently.                                                                     |
| Reduce errors           | &bull; Compile time processing. <br/> &bull; SQL and app always in sync. <br/> &bull; All prepared statement parameters have a default value. <br/> &bull; No SQL injection attacks. |
| No runtime dependencies | There is no NormalSQL runtime. Use the generated app source code as-is.                                                                                                              |
| Easier debugging        | &bull; Statement.toString() returns currrent SQL. <br/> &bull; ResultSet.toString() returns current row's fields.                                                                    |




## Quick Example

Starting with the simple SELECT [SelectForSale.sql](doc/example/SelectForSale.sql):

```sql
-- Find cars by style and mileage 
SELECT id, make, model, year 
  FROM automobiles 
 WHERE style = 'coupe' 
   AND odometer < 100000;
```

NormalSQL generates this prepared statement:

```sql
-- Find cars by style and mileage 
SELECT id, make, model, year 
  FROM automobiles 
 WHERE style = ? 
   AND odometer < ?;
```

The literals `'coupe'` and `100000` were automatically replaced with a parameter `?`.

The prepared statement is then wrapped in generated class [SelectForSale.java](doc/example/SelectForSale.java):

```java
// pseudo-code
class SelectForSale 
{
    String _style = "coupe";
    void setStyle( String style ) { _style = style; }
    
    Integer _odometer = 100000;
    void setOdometer( Integer odometer ) { _odometer = odometer; }

    SelectForSaleResultSet execute();
}
```

Next, NormalSQL finds the columns `id`, `make`, `model`, and `year`. They are added to
to the inner class Row.
[SelectForSaleResultSet.java](doc/example/SelectForSaleResultSet.java) is the 
generated source:

```java
// pseudo-code
class SelectForSaleResultSet implements Iterable<Row>
{
    // inner-class
    class Row
    {
        Integer getID()
        String  getMake()
        String  getModel()
        Integer getYear()
    }
}
```

Your application will use those wrappers something like this:

```java
Connection conn = DriverManager.getConnection( ... );

SelectForSale select = new SelectForSale( conn );
select.setStyle( "sedan" );
select.setOdometer( 90000 );

SelectForSaleResultSet rs = select.execute();

for( SelectForSaleResultSet.Row row : rs )
{
    System.out.println( row.toJSON() );
}

rs.close();
select.close();
```

# Configuration

## Maven

## Command Line

Optionally specify .properties. Optionally specify initial source directory.


## Properties

Use `normalsql.properties` to configure processing. Place in the working
directory, in the source directory, or specify via command line.

```properties
description = MySQL driver for localhost
driver = com.mysql.cj.jdbc.Driver
url = jdbc:mysql://localhost/automobiles?useServerPrepStmts=true
username = root
password =
source = .
target = .
package = .
```

Future: props templates. Future: props strategy for naming accessors.

Source directory. NormalSQL will recurse thru subdirectories.

Target directory. Like the Java compiler does with packages, will mirror the
directory structure of the source directory.

extensions. file extensions to process. default is all.

# Theory of Operation

NormalSQL is very simple:

* Parse SQL statement
* Find predicates and columns
* Infer data types
* Generate source code

## Parse

NormalSQL supports SQL:2016 DML statements (SELECT, INSERT, UPDATE, etc),
including JOINs, Common Table Expressions, and such. It accommodates the syntax
of popular dialects.

All query statements are transformed into prepared statements (see example
above). The target database then does its own parsing, validation, etc.

## Find Predicates

All predicates are found. Not just those within the WHERE clause.

Since the default values are copied from the source SQL, your application can
pick and choose which parameters to override.

TODO is_nullable

##### Comparison Predicate

NormalSQL can only match predicates when just the column name is used, either on
the left or right side. Matched `abc < 10`. Not matched `abc - 10 < 0`.

Future: accessor method includes mneumonic of comparison operator

BETWEEN Predicate
--

Matched: `column BETWEEN 0 AND 10` and `5 BETWEEN low AND high`. Not matched:
every other combination, for example `5 BETWEEN 0 AND high`.

And the generated accessors will be:

IN Predicate
--

Every simple literal value is turned into a parameter, one parameter for each
literal.

    IN ( 1, 2, 3 )

Becomes

    IN ( ?, ?, ? )

And the generated accessors will be:

ANY Predicate
--

Accepts an array of values, working like how you wish IN predicates would. Only
supported by specific databases and JDBC drivers. (Future: list supported)

    ANY ( 1, 2, 3 )

Becomes

    ANY ( ? )

And the generated accessors will be:

Future: LIKE, REGEXP, Postgress Matches
--

### Find Columns

Only the outer most result columns are found and become accessors.

Column aliases are supported.

When column names (or aliases) contain unsupported characters (like
punctuation), a generic accessor is created, like `setColumn1(...)`. (Future.)

Only the first instance of a duplicate column is used.

## Infers data types

ResultSet the JDBC driver supports

## Code Generation

NormalSQL generates code you'd write yourself, if only you had more time.

Velocity template engine is currently used. Other engines are being evaluated.

Currently, there are simple wrappers for PreparedStatement and ResultSet.

Future features:

ResultSet wrappers tailored per use case. Like returning a single value. Or a
single row.

Support Updateable ResultSets.

Have a wrapper create POJOs.

Support multiple statements and resultsets.

Support other languages. PHP, Python, C#, whatever. With templates, any thing
its all feasible. Prioritorize by popularity X ease of port X demand.

### Generated PreparedStatement

Generates a PreparedStatement wrapper with typesafe setters for the conditions.
Capitalization conventions for JavaBean accessors are used. (Future:
configurable.)

The default values are copied from your original SQL. Your application overrides
these values as needed. (No more worrying about forgetting to set that nth
parameter!)

The `.toString()` method returns the actual query. Great for debugging.

Generated ResultSet
--

Generates a ResultSet wrapper with getters for the result columns.
Capitalization conventions for JavaBean accessors are used. (Future:
configurable.)

Column aliases are supported. The query `SELECT apple AS banana` will generate
the accessor `setBanana(...)`.

The `.toString()` method returns all the columns of the current row. Great for
debugging.

# Grammar

[NormalSQL.g4](/src/main/antlr4/normalsql/parse/NormalSQL.g4) is an 
[ANTLR v4](https://github.com/antlr/antlr4) grammar. It 
supports all DML statements from SQL-99 to SQL:2016.
(Exceptions: No index query hints.)

Its two functions are 1) find columns and predicates and 2) replace predicate literals 
with parameters. 
It preserves the entire input stream, including whitespace, comments, and formatting.

Therefore, our grammar is permissive, accepting SQL source which may be rejected by the
target database. 

The robustness of our grammar will improve as we gather more tests cases and real world 
examples. If NormalSQL fails to parse SQL which is accepted by the target database, *please*
file a bug report. Thank you in advance.

The error reporting of our grammar will improve over time.

# Future Work 

Allow custom templates for code generation. Because customer have their own best practices
for JDBC, logging, monitoring, and so forth.

The 2.x release will introduce our own document model. NormalSQL will then be a general purpose
SQL parsing toolkit, useful for other projects.

We have some ideas for how to support dynamic SQL. This will require including NormalSQL as a runtime 
dependency.

NormalSQL can work along side other SQL stacks. This allows incremental adoption of Normal's SQL-first workflow.
Creating examples and tutorials will be a lot of work.

Support other platforms, such as PHP, Python, C#.

Create a standalone native NormalSQL executable to be used by other platform's build chains. Perhaps even 
port NormalSQL to other platforms.

Standup a SQL Fiddle like public instance of NormalSQL, allowing people to casually try things out.


--


Use normal SQL statements.

Remember Data Access Objects (DAOs)? The SQL was right there. How everything worked was obvious. No
black box, no runtime magic, no complicated rules or incantations.

Unfortunately, manually writing and maintaining DAOs was tedious and error prone. So DAOs rightly
fell out of fashion.

But what if that process was automated and error free?

That's exactly what NormalSQL does.

Use normal SQL DML statements as-is to generate app source code.
