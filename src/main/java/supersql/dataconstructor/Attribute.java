package supersql.dataconstructor;

import supersql.extendclass.ExtList;

public class Attribute {
	
	private boolean connector = false;
	private boolean visited = false;
	private boolean isAtt = false;
	
	private double group = -1;
	private int column = -1;
	
	private ExtList tuples = null;
	private ExtList filters = null;
	private ExtList adjList = null;
	
	private String label = null;
	private String table = null;
	private String connTable = null;
	private String where = null;

	public Attribute( String l, String t ) {
		this.label = l;
		this.table = t;
		this.connTable = "";
		this.adjList = new ExtList();
	}
	
	public Attribute( String l, String t, double g) {
		this.label = l;
		this.table = t;
		this.connTable = "";
		this.group = g;
		this.adjList = new ExtList();
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public String getTable()
	{
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setConnector(boolean connector) {
		this.connector = connector;
	}

	public boolean isConnector() {
		return connector;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setConnTable(String connTable) {
		this.connTable = connTable;
	}

	public String getConnTable() {
		return connTable;
	}

	public void setWhere(String where) {
		if ( this.where == null  || where == "" )
		{
			this.where = where;
		}
		else if ( this.where.indexOf( where ) == -1 )
		{
			this.where += " AND " + where;

		}
	}

	public String getWhere() {
		return where;
	}

	public void setAttribute( boolean val )
	{
		isAtt = val;
	}
	
	public boolean isAttribute() {
		return isAtt;
	}

	public void setGroup(double d) {
		this.group = d;
	}

	public double getGroup() {
		return group;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

	public void setTuples(ExtList tuples) {
		this.tuples = new ExtList( tuples );
	}
	
	public int getSize()
	{
		return tuples.size();
	}
	
	public int getColumnCount()
	{
		return ((ExtList) tuples.get(0)).size();
	}
	
	public ExtList getTuple() {

		return (ExtList) ( ((ExtList) tuples.get(0)).get(column) );
	}
	
	public ExtList getTuple(String key, int col) {
		ExtList ret = null;
		
		if ( key.compareTo( "" ) != 0 )
		{
			for ( int i = 0; i < tuples.size(); i++ )
			{
				if ( ((ExtList) tuples.get(i)).get(col).toString().compareTo(key) == 0 )
				{
					ret =  (ExtList) ( ((ExtList) tuples.get(i)).get(column) );
					break;
				}
			}
		}
		return ret;
	}
	
    public ExtList getTuples(String key, int col) {
		
		ExtList fTuples;
		//if ( key.compareTo( "" ) != 0 )
		if ( key != null )
		{ //if there is a key
			fTuples = new ExtList();
			for ( int i = 0; i < tuples.size(); i++ )
			{
				if ( ((ExtList) tuples.get(i)).get(col).toString().compareTo(key) == 0 )
				{
					ExtList temp = new ExtList();
					temp.add(((ExtList) tuples.get(i)).get(column));
					fTuples.add( temp );
				}
			}
			return fTuples;
		}
		else
		{ 
			return tuples;
		}
	}

	public void delTuples(String key, int col) 
	{
		if ( key != null )
		{
		    for ( int i = 0; i < tuples.size(); i++ )
		    {
			    if ( ((ExtList) tuples.get(i)).get(col).toString().compareTo(key) == 0 )
			    {
				    tuples.remove( i );
			    }
		    }
		}
	}
	
	public void setFilter( ExtList filterList )
	{
		filters = filterList;
	}
	
	public ExtList getFilter()
	{
		return filters;
	}
	
	public void connectTo( Attribute a )
	{
		if ( !adjList.contains( a ) )
		{
			this.adjList.add( a );
		}
	}
	
	public ExtList getAdjNodes()
	{
		return adjList;
	}

}

