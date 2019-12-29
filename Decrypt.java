import java.io.*;
import java.text.*;
import java.util.*;


class Decrypt extends Verification{

  public static void main(String[] args){

    if(args.length < 2)
        System.out.println("Veuillez entrer le bon nombre d'arguments");
    else {
      String type = args[0];
      String texte = recup_texte(args[1]);
      texte = texte.toLowerCase();
      HashSet<String> dico = recup_dictionnaire();



    if(type_correct(type)){ //verifie si on a bien un type egal a c ou p ou v
      switch(type){

/****************************Cesar***************************/

        case "c":
          if(args.length < 3)
            System.out.println("Veuillez entrer le bon nombre d'arguments");
          else {
            String strategie = args[2];
            Cesar code;
            if(int_correct(strategie)){

              int strat = Integer.parseInt(strategie);
              int decalage;
              switch (strat) {
                case 1:
                  if(args.length != 4)
                    System.out.println("Veuillez entrer le bon nombre d'arguments");
                  else {
                    String mot_connu = args[3];
                    code = new Cesar(texte, mot_connu);
                    decalage = code.motConnu();
                    code.setDistance(decalage);
                    System.out.println(code.dechiffrer());
                    break;
                  }
                case 2:

                  code = new Cesar(texte, dico);
                  decalage = code.frequenceLettre();
                  if(decalage == -1){
                    System.out.println("erreur");
                    break;
                  }
                  else{
                    code.setDistance(decalage);
                    System.out.println(code.dechiffrer());
                    break;
                  }
                case 3:
                  code = new Cesar(texte, dico);
                  decalage = code.forceBrute();
                  if(decalage == -1){
                    System.out.println("erreur");
                    break;
                  }
                  else{
                    code.setDistance(decalage);
                    System.out.println(code.dechiffrer());
                    break;
                  }
            }

          }

        }
        break;

        case "v":
          if(args.length != 3)
            System.out.println("erreur");
          else{
            String tailleTemp = args[2];
            int taille = Integer.parseInt(tailleTemp);
            Vigenere code = new Vigenere(texte,dico);
            String motDecr = code.decrypte(taille);

            System.out.println(motDecr);
          }
      }
    }
  }
}
}
