package digitalBank;

public class Client {
    private String name;
    private String cpf;
    private int age;
    
    public Client(String name, String cpf, int age){
        this.name = name;
        this.cpf = cpf;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public int getAge() {
        return age;
    }

}
