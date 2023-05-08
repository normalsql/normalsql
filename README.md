# NormalSQL

Compiles SQL into prepared statements with type-safe wrappers.

NormalSQL is neither an object/relational mapper (eg Hibernate) or an 
abstraction layer (eg JOOQ). Nor is it a template engine (eg JDBCTemplate,
myBatis).

NormalSQL is a SQL-first workflow. Resolving the database-to-application
interface at *compile time* instead of runtime.

Start with normal SQL. Manage your SQL source code as simple files.
Use your favorite SQL client (eg DBeaver, DataGrip). 

Then NormalSQL generates the Java wrappers.

### Simple Example

Here's a simple example, to show the work flow.

Start with SQL SELECT statement GetPeopleOlderThan.sql:

```sql
SELECT name FROM people WHERE age > 18
```

Becomes this parameterized query string:

```java
"SELECT name FROM people WHERE age > ?"
```

Which is used in this generated PreparedStatement wrapper:

```java
// pseudo-code
class GetPeopleOlderThan 
{
	String _sql = "SELECT name FROM people WHERE age > ?";
    int _age = 18;
    void setAgeGT( int age ) { _age = age; }
    
    GetPeopleOlderThanResultSet execute() { ... }
}
```

Finally, it generates this ResultSet wrapper:

```java
// pseudo-code
class GetPeopleOlderThanResultSet implements Iterable<Row>
{
    // inner-class
    class Row
    {
        String getName();
    }
}
```

Your application would look something like this:

```java
Connection conn = DriverManager.getConnection( ... );

GetPeopleOlderThan select = new GetPeopleOlderThan( conn );
select.setAgeGT( 21 );

GetPeopleOlderThanResultSet rs = select.execute();

for( GetPeopleOlderThanResultSet.Row row : rs )
{
    System.out.println( row.getName() );
}

rs.close();
select.close();
```

What could be easier?

### Benefits

Fewer errors. As your SQL changes over time,
NormalSQL leverages the Java compiler to catch and prevent errors.

Delegate to DBAs. SQL source code can now be managed by domain experts,
like DBAs and Business Analysts. 

No runtime dependencies. There is no NormalSQL runtime. Use generated 
source code as-is.

Prevent SQL injection attacks. NormalSQL only uses Prepared Statements.

Aid debugging. PreparedStatement toString() outputs the current query, for easy cut & paste into your SQL client. ResultSet toString() 

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


## Example Project

# Usage

## Maven Plugin

TODO

## Command Line

NormalSQL uses metadata to infer data types of columns and predicates. It
requires a live running instance of the target database(s) during processing.



### Command Line

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

Future: config templates. Future: config strategy for naming accessors.

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


