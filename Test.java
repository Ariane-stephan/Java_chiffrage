class Test extends Verification{

  public static void main(String[] args){
    String texte = recup_texte(args[0]);

    System.out.println(texte.length());
  }
}
