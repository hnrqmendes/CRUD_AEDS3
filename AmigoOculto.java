
import java.util.Scanner;

public class AmigoOculto {
  public static Scanner ler = new Scanner(System.in);
  public static void main(String args[]) {
    System.out.println("Teste");
    try {
      CRUD amigoOculto = new CRUD("amigos");
      int entrada;
      do {
        System.out.println("Amigo Oculto");
        System.out.println("================");
        System.out.println("1) Acesso ao sistema");
        System.out.println("2) Novo usuário");
        System.out.println("\n0) Sair");
        System.out.print("\nOpção: ");
        entrada = ler.nextInt();
        ler.nextLine();
        if (entrada == 1) {
          acesso(amigoOculto);
        } else if (entrada == 2) {
          novoUsuario(amigoOculto);
        }
      } while (entrada != 0);
      ler.close();
    } catch (Exception e) {
      System.out.println("erro");
      e.printStackTrace();
    }
  }
  public static void acesso(CRUD amigoOculto) throws Exception{
    System.out.println("\nACESSO AO SISTEMA");
    System.out.print("\nEmail: ");
    String email = ler.nextLine();
    if (amigoOculto.read(email) == null){
      System.out.println("\nUsuário não cadastrado");
    }
    else{
      System.out.print("Senha: ");
      String senha=ler.nextLine();
      Usuario temp=amigoOculto.read(email);
      if(temp.getSenha().compareTo(senha)==0){
        System.out.println("\nMenu não finalizado\n");
        System.out.println("Pressione qualquer tecla para continuar");
        String a=ler.nextLine();
        if(a!=null){
          return;
        }
      }
      else{
        System.out.println("\nSenha Incorreta");
        System.out.println("Pressione qualquer tecla para continuar");
        String a=ler.nextLine();
        if(a!=null){
          acesso(amigoOculto);
        }
      }
    }
  }
  public static void novoUsuario(CRUD amigoOculto) throws Exception {
    System.out.println("\nNOVO USUÁRIO");
    System.out.print("\nEmail: ");
    String email = ler.nextLine();
    if (email == "") {
      System.out.println("\nUsuário Invalido");
    } else {
      if (amigoOculto.read(email) == null) {
        System.out.print("\nNome: ");
        String nome = ler.nextLine();
        System.out.print("\nSenha: ");
        String senha = ler.nextLine();
        System.out.println("\nEmail: " + email + " Nome: " + nome + " Senha: " + senha);
        System.out.print("Digite 1 se deseja confirmar o cadastro: ");
        int confirmar = ler.nextInt();
        ler.nextLine();
        if (confirmar == 1) {
          amigoOculto.create(nome, email, senha);
          System.out.println("Usuário cadastrado com sucesso\n");
          System.out.println("Pressione qualquer tecla para continuar");
          String a=ler.nextLine();
          if(a!=null){
            return;
          }
        } else {
          System.out.println("Cadastro cancelado\n");
          System.out.println("Pressione qualquer tecla para continuar");
          String a=ler.nextLine();
          if(a!=null){
            return;
          }
        }
      } else {
        System.out.println("ERRO! Email já cadastrado");
        System.out.println("Pressione qualquer tecla para continuar");
        String a=ler.nextLine();
        if(a!=null){
          novoUsuario(amigoOculto);
        }
      }
    }
  }
}

