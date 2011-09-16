
/**

Allergies.java   Thu Sep 08 14:31:38 PDT 2011
  
Generated using Fado's Select template. Original SQL:
    
SELECT
	m.Facility,
	a.Description,
	a.Type,
	a.Severity,
	a.Reaction,
	a.DiscoveredDate
FROM 
	Allergy a, 
	Message m
WHERE
	a.message_ID = m.id AND
	m.MessageType = 'ADT' AND
	a.PatientKey_ID IN ( 1, 2, 4, 5, 6, 7 )
ORDER BY a.description, a.discoveredDate, a.reaction

@see ./demo/ganesh/Allergies.sql
  

**/

package play;

import java.io.StringWriter;
import java.io.PrintWriter;
import java.sql.*;

public class 
	AllergiesPlus
{
	protected Connection __connection = null;
	
	public AllergiesPlus( Connection connection )
	{
		__connection = connection;
	}
	
	private int __scrolling = ResultSet.TYPE_SCROLL_SENSITIVE;
	
	public void setScrolling( int scrolling )
	{
		try
		{
			close();
		}
		catch( Exception e ) {}
		__scrolling = scrolling;
	}
	
	public int getScrolling()
	{
		return __scrolling;
	}
	
	private int __concurrency = ResultSet.CONCUR_UPDATABLE;
	
	public void setConcurrency( int concurrency )
	{
		try
		{
			close();
		}
		catch( Exception e ) {}
		__concurrency = concurrency;
	}
	
	public int getConcurrency()
	{
		return __concurrency;
	}
	
	
	protected PreparedStatement __prepared = null;

	protected final PreparedStatement getPreparedStatement()
		throws SQLException
	{
		if( __prepared == null )
		{
			String sql = getSQL();
			int scrolling = getScrolling();
			int concurrency = getConcurrency();
			__prepared = __connection.prepareStatement( sql, scrolling, concurrency );

		}
		return __prepared;
	}

	protected boolean __executed = false;
	
	public final boolean execute()
		throws SQLException
	{
		boolean success = false;
		if( !__executed )
		{
			__executed = true;
			PreparedStatement statement = getPreparedStatement();
			setParameters( statement );
			success = statement.execute();
			if( success )
			{
				__resultSet = statement.getResultSet();
			}
		}
		return success;
	}

	/**
	 * Done with current response (ResultSet), clears this instance, prior to reuse.
	 * 
	 * @throws SQLException
	 */
	public void close()
		throws SQLException
	{
		getNextRow( false );
		if( __resultSet != null )
		{
			__resultSet.close();
			__resultSet = null;
		}
		__executed = false;
		
	}

	protected ResultSet __resultSet = null;

	public final ResultSet getResultSet()
	{
		return __resultSet;
	}
	
	public final boolean next()
		throws SQLException
	{
		execute();
		
		boolean success = false;
		if( __resultSet != null )
		{
			success = __resultSet.next();
			getNextRow( success );

		}
		return success;
	}
	
	public int count()
	{
		int result = 0;
		try
		{
			execute();
			ResultSet rs = getResultSet();
			int current = rs.getRow();
			if( rs.last() )
			{
				result = rs.getRow();
				if( current == 0 )
				{
					rs.beforeFirst();
				}
				else
				{
					rs.absolute( current );
				}
			}
				
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
		return result;
	}
	
	private int _id = 0;
	private String _actionCode = null;
	private String _code = null;
	private String _description = null;
	private java.sql.Timestamp _discoveredDate = null;
	private String _reaction = null;
	private String _sensitivityDescription = null;
	private String _severity = null;
	private String _severityDescription = null;
	private String _status = null;
	private String _type = null;
	private int _message_ID = 0;
	private int _patientKey_ID = 0;
	
	
	public void setID( int id ) { _id = id; }
	public int getID() { return _id; }
	
	public void setActionCode( String actionCode )
		throws SQLException
	{
		if( __inserting || __updating )
		{
			_actionCode = actionCode;
		}
		else
		{
			throw new SQLException( "ResultSet not updatable." );
		}
	}
	public String getActionCode() { return _actionCode; }
	
	public void setCode( String code ) { _code = code; }
	public String getCode() { return _code; }
	
	public void setDescription( String description )
	{
		_description = description;
	}
	
	public String getDescription()
	{
		return _description;
	}

	public void setDiscoveredDate( java.sql.Timestamp discoveredDate )
	{
		_discoveredDate = discoveredDate;
	}
	
	public java.sql.Timestamp getDiscoveredDate()
	{
		return _discoveredDate;
	}	
	
	public void setReaction( String reaction )
	{
		_reaction = reaction;
	}
	
	public String getReaction()
	{
		return _reaction;
	}
	
	public void setSensitivityDescription( String sensitivityDescription ) { _sensitivityDescription = sensitivityDescription; }
	public String getSensitivityDescription() { return _sensitivityDescription; }
	
	public void setSeverity( String severity )
	{
		_severity = severity;
	}
	
	public String getSeverity()
	{
		return _severity;
	}	
	
	public void setSeverityDescription( String severityDescription )
	{
		_severityDescription = severityDescription;
	}
	
	public String getSeverityDescription()
	{
		return _severityDescription;
	}	
	
	public void setStatus( String status ) { _status = status; }
	public String getStatus() { return _status; }
	
	public void setType( String type )
	{
		_type = type;
	}
	
	public String getType()
	{
		return _type;
	}

	public void setMessageID( int messageID ) { _message_ID = messageID; }
	public int getMessageID() { return _message_ID; }
	
	public void setPatientKeyID( int patientKey_ID ) { _patientKey_ID = patientKey_ID; }
	public int getPatientKeyID() { return _patientKey_ID; }
	
	private void getNextRow( boolean success )
		throws SQLException
	{
		if( success )
		{
			ResultSet rs = getResultSet();
			_id = rs.getInt( "ID" );
			_actionCode = rs.getString( "ActionCode" );;
			_code = rs.getString( "Code" );;
			_description = rs.getString( "Description" );
			_discoveredDate = rs.getTimestamp( "DiscoveredDate" );
			_reaction = rs.getString( "Reaction" );
			_sensitivityDescription = rs.getString( "SensitivityDescription" );;
			_severity = rs.getString( "Severity" );
			_severityDescription = rs.getString( "SeverityDescription" );;
			_status = rs.getString( "Status" );;
			_type = rs.getString( "Type" );
			_message_ID = rs.getInt( "Message_ID" );
			_patientKey_ID = rs.getInt( "PatientKey_ID" );
		}
		else
		{
			_id = 0;
			_actionCode = null;
			_code = null;
			_description = null;
			_discoveredDate = null;
			_reaction = null;
			_sensitivityDescription = null;
			_severity = null;
			_severityDescription = null;
			_status = null;
			_type = null;
			_message_ID = 0;
			_patientKey_ID = 0;
		}
	}
	
	
	
	public String _messageTypeParam = "ADT";
	
	public void setMessageTypeParam( String messageType )
	{
		_messageTypeParam = messageType;
	}
	
	public String getMessageTypeParam()
	{
		return _messageTypeParam;
	}

	private boolean __inserting = false;
	
	/**
	 * Tries to start inserting mode. Throws SQLException if 
	 * 
	 * @throws SQLException
	 */
	public void startInsert()
		throws SQLException
	{
		execute();
		ResultSet rs = getResultSet();
		int concurrency = rs.getConcurrency();
		if( concurrency == ResultSet.CONCUR_UPDATABLE )
		{
			__inserting = true;
			_id = 0;
			_actionCode = null;
			_code = null;
			_description = null;
			_discoveredDate = null;
			_reaction = null;
			_sensitivityDescription = null;
			_severity = null;
			_severityDescription = null;
			_status = null;
			_type = null;
			_message_ID = 0;
			_patientKey_ID = 0;
		}
		else
		{
			throw new SQLException( "ResultSet not updatable." );
		}
	}
	
	public void finishInsert()
		throws SQLException
	{
		if( __inserting )
		{
			__inserting = false;
			ResultSet rs = getResultSet();
			rs.moveToInsertRow();
			rs.updateInt( "ID", _id );
			rs.updateString( "ActionCode", _actionCode );
			rs.updateString( "Code", _code );
			rs.updateString( "Description", _description );
			rs.updateTimestamp( "DiscoveredDate", _discoveredDate );
			rs.updateString( "Reaction", _reaction );
			rs.updateString( "SensitivityDescription", _sensitivityDescription );
			rs.updateString( "Severity", _severity );
			rs.updateString( "SeverityDescription", _severityDescription );
			rs.updateString( "Status", _status );
			rs.updateString( "Type", _type );
			rs.updateInt( "Message_ID", _message_ID );
			rs.updateInt( "PatientKey_ID", _patientKey_ID );
			rs.insertRow();
			rs.moveToCurrentRow();
			rs.next();
			getNextRow( true );
		}
		else
		{
			throw new SQLException( "call startInset() before finishInsert()" );
		}
	}
	
	
	private boolean __updating = false;
	
	public void startUpdating()
		throws SQLException
	{
		execute();
		ResultSet rs = getResultSet();
		int concurrency = rs.getConcurrency();
		if( concurrency == ResultSet.CONCUR_UPDATABLE )
		{
			__updating = true;
		}
		else
		{
			throw new SQLException( "ResultSet not updatable." );
		}
	
	}
	
	public void finishUpdating()
		throws SQLException
	{
		if( __updating )
		{
			__updating = false;
			ResultSet rs = getResultSet();
			rs.updateInt( "ID", _id );
			rs.updateString( "ActionCode", _actionCode );
			rs.updateString( "Code", _code );
			rs.updateString( "Description", _description );
			rs.updateTimestamp( "DiscoveredDate", _discoveredDate );
			rs.updateString( "Reaction", _reaction );
			rs.updateString( "SensitivityDescription", _sensitivityDescription );
			rs.updateString( "Severity", _severity );
			rs.updateString( "SeverityDescription", _severityDescription );
			rs.updateString( "Status", _status );
			rs.updateString( "Type", _type );
			rs.updateInt( "Message_ID", _message_ID );
			rs.updateInt( "PatientKey_ID", _patientKey_ID );
			rs.updateRow();
		}
		else
		{
			throw new SQLException( "call startInset() before finishInsert()" );
		}
	
	}


	private int[] _patientKey_IDList = new int[] { 1, 2, 4, 5, 6, 7,  };
	private int _patientKey_IDCount = 0;
	public void addPatientKey_IDParam( int patientKey_ID )
	{
		if( _patientKey_IDCount == _patientKey_IDList.length )
		{
			throw new IndexOutOfBoundsException( "IN condition parameter array only has room for: " + _patientKey_IDList.length );
		}
		int count = _patientKey_IDCount;
		
		// Parameter array get filled to the right, so there are no holes
		for( int i = count; count < _patientKey_IDList.length; count++ )
		{
			_patientKey_IDList[i] = patientKey_ID;
		}
		_patientKey_IDCount++;
	}
	
	public int getPatientKey_IDParam( int nth )
	{
		return _patientKey_IDList[ nth ];
	}
	

	private static String __sql = null;
	
	public String getSQL()
	{
		if( __sql == null )
		{
			StringWriter writer = new StringWriter();
			PrintWriter w = new PrintWriter( writer );
			w.println( "SELECT" );
			w.println( "	a.Id," );
			w.println( "	a.ActionCode," );
			w.println( "	a.Code," );
			w.println( "	a.Description," );
			w.println( "	a.DiscoveredDate," );
			w.println( "	a.Reaction," );
			w.println( "	a.SensitivityDescription," );
			w.println( "	a.Severity," );
			w.println( "	a.SeverityDescription," );
			w.println( "	a.Status," );
			w.println( "	a.Type," );
			w.println( "	a.Message_ID," );
			w.println( "	a.PatientKey_ID" );
			w.println( "FROM " );
			w.println( "	Allergy a, " );
			w.println( "	Message m" );
			w.println( "WHERE" );
			w.println( "	a.message_ID = m.id AND" );
			w.println( "	m.MessageType = ? AND" );
			w.println( "	a.PatientKey_ID IN ( ?, ?, ?, ?, ?, ? )" );
			w.println( "ORDER BY a.description, a.discoveredDate, a.reaction" );
			w.close();
			__sql = writer.toString();
		}
		return __sql;
	}
	
	protected void setParameters( PreparedStatement prepared )
		throws SQLException
	{
		prepared.setString( 1, getMessageTypeParam() );
		prepared.setInt( 2, getPatientKey_IDParam( 0 ));
		prepared.setInt( 3, getPatientKey_IDParam( 1 ));
		prepared.setInt( 4, getPatientKey_IDParam( 2 ));
		prepared.setInt( 5, getPatientKey_IDParam( 3 ));
		prepared.setInt( 6, getPatientKey_IDParam( 4 ));
		prepared.setInt( 7, getPatientKey_IDParam( 5 ));
	}

	public String toString()
	{
		String result = getSQL();
		result = result.replaceFirst( "\\?", quotifyString( getMessageTypeParam() ));
		result = result.replaceFirst( "\\?", quotifyString( getPatientKey_IDParam( 0 )));
		result = result.replaceFirst( "\\?", quotifyString( getPatientKey_IDParam( 1 )));
		result = result.replaceFirst( "\\?", quotifyString( getPatientKey_IDParam( 2 )));
		result = result.replaceFirst( "\\?", quotifyString( getPatientKey_IDParam( 3 )));
		result = result.replaceFirst( "\\?", quotifyString( getPatientKey_IDParam( 4 )));
		result = result.replaceFirst( "\\?", quotifyString( getPatientKey_IDParam( 5 )));
		return result;
	}
	
	private String quotifyString( Object value )
	{
		if( value instanceof String )
		{
			return "'" + value.toString() + "'";
		}
		else
		{
			return value.toString();
		}
	}

}
