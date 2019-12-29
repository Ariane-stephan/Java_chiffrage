import java.io.*;
import java.text.*;
import java.util.*;
import java.lang.*;

class Verification{
  private static String [] nouveau_fichiers;

/********************methode pour recupere le texte d'un fichier*******************/

  public static String recup_texte(String fichier){
    String texte = "";
    BufferedReader lecture = null;
    try{
      lecture = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fichier))));
      int i = lecture.read(); //lit le fichier
      while(i != -1){ //tant qu'on est pas arrive a la fin
        texte += (char)(i); //rajoute le char dans le string
        i = lecture.read(); //passe au caractere suivant
      }
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      if (lecture != null)
        lecture.close(); //ferme le fichier
    } catch (IOException e) {
      e.printStackTrace();
    }

    return texte;
  }


  static boolean type_correct(String type){
    boolean test = false;
    if (type.equals("c") || type.equals("p") || type.equals("v"))
      test = true;
    return test;
  }


/*****************methode pour tester si un string est un int***********/

  static boolean int_correct(String cle){
    boolean test = true;
       try{
         Integer.parseInt(cle); //essaye de le parser en int
       } catch(NumberFormatException e){
         test = false; //s'il y a une erreur passe le boolean a false
       }
       return test;
  }

/******************methode pour tester si un string est un mot***********/

  static boolean string_type(String cle){
    boolean test = cle.matches("[a-zA-Z]+"); //verifie si le string matches avec l'alphabet
    return test;
  }


    public static HashSet<String> recup_dictionnaire(){

      HashSet<String> hs = new HashSet<String>();
      try{

        BufferedReader br = new BufferedReader(new FileReader(new File("words.txt")));
        String i = br.readLine();
        while (i != null){
          hs.add(i);
          i = br.readLine();
        }
        br.close();
      }
      catch (FileNotFoundException e){
        e.printStackTrace();
      }
      catch (IOException e){
        e.printStackTrace();
      }
      return hs;
    }
}
