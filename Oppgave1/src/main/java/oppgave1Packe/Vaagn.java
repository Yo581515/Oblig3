package oppgave1Packe;

import java.util.ArrayList;
import java.util.List;

public class Vaagn {
    
    private List<String> varer = new ArrayList<>();
    
    
    
    public Vaagn() {
	}

	public void addVare(String item) {
    	varer.add(item);
    }
    
    public List<String> getVarer() {
        return varer;
    }
    
    
    public void removeAtIndex(int indeks) {
    	varer.remove(indeks);
    }
    
    public int Vaagnlength() {
    	return varer.size();
    }
    
    public String vareAtIndex(int index) {
    	return varer.get(index);
    }
    
    
}
