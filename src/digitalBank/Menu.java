package digitalBank;

public class Menu {
    public Menu(){
        Bank bank = new Bank();

        boolean exit = false;
        while (!exit) {
            menu();
            int option = bank.optionInt();
            switch (option) {
                case 1:
                    bank.creatClient();
                    break;
                case 2:
                    bank.createAccount();
                    break;
                case 3:
                    bank.deposit();
                    break;
                case 4:
                    bank.withdraw();
                    break;
                case 5:
                    bank.transfer();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Opção Inválida.");
                    break;
            }

        }
    }

    private void menu() {
        String menu = Color.GREEN + """
                1. Cadastrar cliente
                2. Cadastrar conta
                3. Depositar
                4. Sacar
                5. Transferir
                6. Sair
                Selecione uma opção.""" + Color.RESET;
        System.out.println(menu);
    }
}
