
/*Programação Orientada a Objetos - Atividade Prática Supervisionada
Professor: Marcelo Perantoni
Alunos: Erick Klava de Oliveira (2022100432)
        Jorge André de Oliveira Santos (2017201294)  
        Pedro Maciel Dantas Milck (2022100318)
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
    


//Classe Funcionário
 class Funcionario 
{
    protected String nome;
    protected int idade;
    protected String cargo;
    protected double salario;

    public Funcionario(String nome, int idade, String cargo, double salario) 
    {
        this.nome = nome;
        this.idade = idade;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getNome() 
    {
        return nome;
    }

    public int getIdade() 
    {
        return idade;
    }

    public String getCargo() 
    {
        return cargo;
    }

    public double getSalario() 
    {
        return salario;
    }

    //Método para exibição das informações do Funcionário
    @Override
    public String toString() 
    {
        return "Nome: " + nome + "\nIdade: " + idade + "\nCargo: " + cargo + "\nSalário: R$" + String.format("%.2f", salario);
    }
}

//Classe Gerente herdando de funcionário
 class Gerente extends Funcionario 
{
    private double bonus;

    public Gerente(String nome, int idade, double salario, double bonus) 
    {
        super(nome, idade, "Gerente", salario);
        this.bonus = bonus;
    }

    public double getBonus() 
    {
        return bonus;
    }

    @Override
    public String toString() 
    {
        return super.toString() + "\nBônus: R$" + String.format("%.2f", bonus);
    }
}

//Classe para cadastro de funcionários
public class CadastroFuncionarios 
{
    //Criação do array de funcionários e objeto scanner
    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) 
    {
        menu();
    }

    public static void menu() 
    {
        while (true) 
        {
            System.out.println("\n---Cadastro de Funcionários---");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar Funcionários");
            System.out.println("3 - Buscar funcionário");
            System.out.println("4 - Excluir funcionário");
            System.out.println("5 - Sair");
            System.out.print(">> ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) 
            {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    listarFuncionarios();
                    break;
                case 3:
                    buscarFuncionario();
                    break;
                case 4:
                    excluirFuncionario();
                    break;
                case 5:
                    System.out.println("Saindo . . .");
                    scanner.close();
                    return;
                default:
                    System.out.println("= = = Opção inválida, tente novamente. = = = ");
            }
        }
        
    }
    //Função que espera o usuário apertar Enter, implementada para evitar replicação de código.
    public static void pressEnter()
    {
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }

    //Método de cadastro
    public static void cadastrarFuncionario() 
    {
        System.out.print("Digite o nome: ");
        String nome = null;
        
            nome = scanner.nextLine();
            if (!nome.matches("[a-zA-Z\\s]+")) // Verifica se o nome contém apenas letras (maiúsculas ou minúsculas) e espaços
            {
                System.out.println("= = = O nome deve conter apenas letras e espaços. = = =");
                return;
            }


        
        int idade = 0;
        while(true)
        {
            try
        {
            System.out.print("Digite a idade: ");
            idade = scanner.nextInt();
            scanner.nextLine();
            if(idade > 0)
            {
                break;
            }
            else
                {
                    System.out.println("= = = A idade deve ser um número positivo. = = =");
                }
        }
         catch(InputMismatchException e)
         {
            System.out.println("= = = Erro: a idade deve ser um número inteiro. = = =");
            scanner.nextLine();
            return;
         }

        }
        String cargo = null;
        while(true)
        {
        System.out.print("Digite o cargo: ");
        
            cargo = scanner.nextLine();
            if(cargo.matches("[a-zA-Z\\s]+"))
                break;
            
            else
            System.out.println("= = = O cargo deve conter apenas letras e espaços. = = =");
        }
            
        double salario = 0;
        while(true)
        {
            System.out.print("Digite o salário: ");
        
        try
        {
            salario = scanner.nextDouble();
            scanner.nextLine();
            

            if(salario > 0)
            {
                break;
            }
            else
            {
                System.out.println("= = = O salário deve ser um número positivo. = = = ");
            }
        }
         catch(InputMismatchException e)
         {
            System.out.println("= = = Valor inválido digitado para o salário. = = = ");
            scanner.nextLine();
            
         }
        }
        
        //Condicional para a captura do bônus, no caso do funcionário cadastro ser gerente
        if (cargo.equalsIgnoreCase("gerente")) 
        {
            System.out.println("Digite o bônus do(a) gerente: ");
            double bonus = scanner.nextDouble();
            scanner.nextLine(); 

            Gerente gerente = new Gerente(nome, idade, salario, bonus);
            funcionarios.add(gerente);
            System.out.println("Gerente " + nome + " cadastrado(a).");
        } 
        else 
        {
            Funcionario funcionario = new Funcionario(nome, idade, cargo, salario);
            funcionarios.add(funcionario);
            System.out.println("\n= = = Funcionário(a) " + nome + " cadastrado(a). = = =\n");
            pressEnter();
        }
    }

    //Método de listar funcionários
    public static void listarFuncionarios() 
    {
        if (funcionarios.isEmpty()) 
        {
            System.out.println("= = = Nenhum(a) funcionário(a) cadastrado(a). = = =");
        } 
        else 
        {
            System.out.println("\nLista de Funcionários:");
            for (int i = 0; i < funcionarios.size(); i++) 
            {
                System.out.println((i + 1) + ". " + funcionarios.get(i) + "\n- - - - - - -");
            }
        }
        pressEnter();

    }

    //Método para buscar um funcionário
    public static void buscarFuncionario() 
    {
        System.out.println("Digite o nome do funcionário a ser buscado: ");
        String nomeBusca = scanner.nextLine();
        boolean encontrado = false;

        for (Funcionario func : funcionarios) 
        {
            if (func.getNome().equalsIgnoreCase(nomeBusca)) 
            {
                System.out.println(func);
                encontrado = true;
                pressEnter();
                break;
            }
        }

        if (!encontrado) 
        {
            System.out.println("= = = Funcionário(a) " + nomeBusca + " não encontrado(a). = = =");
            pressEnter();
        }
    }
    //Método para excluir um funcionário
    public static void excluirFuncionario()
    {
        System.out.println("Digite o nome do funcionário a ser excluido: \n>>");
        String nomeBusca = scanner.nextLine();
        boolean encontrado = false;

        for (int i = 0; i < funcionarios.size(); i++)
        {
            Funcionario func = funcionarios.get(i);

            if(func.getNome().equalsIgnoreCase(nomeBusca))
            {
                funcionarios.remove(i);
                System.out.println("= = = Funcionário(a) " + nomeBusca + " excluído(a).= = = ");
                encontrado = true;
                break;
            }
        }
        
        if(!encontrado)
        {
            System.out.println("= = = Funcionário" + nomeBusca + "não encontrado. = = =");
        }

        
    }
}