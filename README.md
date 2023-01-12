# NormalSQL

Tranforms your normal SQL *as-is* into simple type-safe application source
code. 

No object/relation mapping, no impedance mismatches, no abstraction layer, no N+1 lazy initialization, no special syntax, and no runtime dependencies.

Just your application and your normal SQL.

## Example

Starting with this simple query
[`SelectForSale.sql`](doc/example/SelectForSale.sql):

```sql
SELECT id, make, model, year 
  FROM automobiles 
 WHERE style = 'coupe' 
   AND odometer < 100000;
```

NormalSQL finds the columns `id`, `make`, `model`, and `year`. Ditto the
predicates `style = 'coupe'` and `odometer < 100000`. Each literal value is
replaced with a parameter `?`, creating this prepared statement:

```sql
SELECT id, make, model, year 
  FROM automobiles 
 WHERE style = ? 
   AND odometer < ?;
```

Classes [SelectForSale.java](doc/example/SelectForSale.java) and
[SelectForSaleResultSet.java](doc/example/SelectForSaleResultSet.java) are
generated. Here's pseudo-code showing the typesafe accessors:

```java
class SelectForSale 
{
    String _style = "coupe";
    void setStyle( String style ) { _style = style; }
    
    Integer _odometer = 100000;
    void setOdometer( Integer odometer ) { _odometer = odometer; }

    SelectForSaleResultSet execute() { … }
}

class SelectForSaleResultSet
{
    Integer getID() 
    String  getMake() 
    String  getModel()
    Integer getYear()
}
```

Your application will use those classes like this:

```java
Connection conn = DriverManager.getConnection( ... );

SelectForSale select = new SelectForSale( conn );
select.setStyle( "sedan" );
select.setOdometer( 90000 );

SelectForSaleResultSet rs = select.execute();
while( rs.hasNext() )
{
    System.out.printf( 
        "id %d, make %s, model %s, year %d", 
        rs.getID(), rs.getMake(), rs.getModel(), rs.getYear() 
    );
}
rs.close();
select.close();
```

## Usage

NormalSQL uses metadata to infer data types of columns and predicates. It
requires a live running instance of the target database(s) during processing.

For future, depending on feedback, NormalSQL may also support inferring data
types from the statement queries, when the target database is not available
during processing. This method cannot be as precise as using metadata, but may
be sufficient and a bit more convenient.

### Command Line

Optionally specify .properties. Optionally specify initial source directory.

### Maven Plugin

TODO

### Configuration

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

### Theory of Operation

NormalSQL is very simple:

* Parse SQL statement
* Find predicates and columns
* Infer data types
* generate source code

#### Parse

NormalSQL supports SQL:2016 DML statements (SELECT, INSERT, UPDATE, etc),
including JOINs, Common Table Expressions, and such. It accommodates the syntax
of popular dialects.

All query statements are transformed into prepared statements (see example
above). The target database then does its own parsing, validation, etc.

#### Find Predicates

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

    IN ( 1, 2, 3 )

Becomes

    IN ( ?, ?, ? )

And the generated accessors will be:

Future: LIKE, REGEXP, Postgress Matches
--

Future releases.

### Find Columns

Only the outer most result columns are found and become accessors.

Column aliases are supported.

When column names (or aliases) contain unsupported characters (like
punctuation), a generic accessor is created, like `setColumn1(...)`. (Future.)

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

[`NormalSQL.g4`](/src/main/antlr4/normalsql/parse/NormalSQL.g4) is an 
[ANTLR v4](https://github.com/antlr/antlr4) grammar. It 
supports all DML statements from SQL-99 to SQL:2016.
(Exceptions: No index query hints.)

Its two goals are 1) find columns and predicates and 2) replace literals with parameters. 
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


# Comparison

NormalSQL SQL -> Java

ORMs Annotations Mappings -> Magic -> SQL Schemas HQL

Abstraction Layers Java Fluent API -> Magic -> SQL

Templates SQL -> Templates + Java -> SQL Mappings

Active Record / LINQ Mappings Query Expression -> SQL
