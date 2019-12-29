class Dechiffre extends Verification{

  public static void main(String[] args){

    if(args.length != 3){ //verifie si on a bien trois arguments, a approfondir
      System.out.println("Probleme arguments");
    }
    else{
      String type = args[0];
      String cle = args[1];
      String texte = recup_texte(args[2]);

      if(type_correct(type)){ //verifie si on a bien un type egal a c ou p ou v
        switch(type){
          case "c": //cas de cesar

            if(int_correct(cle)){ //verifie si on a bien un int
              int cle_int = Integer.parseInt(cle); //transforme la cle en type int
              Cesar code = new Cesar(texte, cle_int); //cree un code cesar avec les arguments
              System.out.println(code.dechiffrer()); //affiche le texte chiffre
              break;
            }
          else
            System.out.println("Veuillez entrer une cle correcte");

          case "p":
          case "v":
            if(string_type(cle)){ //verifie si on a bien un string
              Vigenere code = new Vigenere(texte, cle); //cree un code Vigenere avec les arguments
              System.out.println(code.dechiffrer()); //affiche le code chiffre
            }
            else
              System.out.println("Veuillez entrer un mot clef correct");
        }
      }
    }
  }
}
