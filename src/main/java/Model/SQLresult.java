package Model;

import java.util.ArrayList;
import java.util.List;

public class SQLresult {
    private boolean hasResultSet;
    private List<String> columns;
    private List<List<String>> rows;
    private int updateCount;
    private String errorMessage;
    
    public SQLresult() {
    columns = new ArrayList<>();
    rows = new ArrayList<>();
}
    
    public void setHasResultSet(boolean hasResultSet){
        this.hasResultSet = hasResultSet;
    }
    
    public void setColumns(List<String> columns){
        this.columns = columns;
    }
    
    public void setRows(List<List<String>> rows){
        this.rows = rows;
    }
    
    public void setUpdateCount(int updateCount){
        this.updateCount = updateCount;
    }
    
    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
    
    public boolean getHasResultSet(){
        return this.hasResultSet;
    }
    
    public List<String> getColumns (){
        return this.columns;
    }
    
    public List<List<String>> getRows (){
        return this.rows;
    }
    
    public int getUpdateCount (){
        return this.updateCount;
    }
    
    public String getErrorMessage (){
        return this.errorMessage;
    }
    
    
    
    
    
    
    
    

  
}