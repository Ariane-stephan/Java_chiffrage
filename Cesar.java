import java.util.*;
import java.io.*;

public class Cesar implements Interface {

  private String texte;
  private HashSet<String> dictionnaire;
  private int distance;
  private String motConnu;


/****************CONSTRUCTEUR POUR LE CHIFFRAGE ET DECHIFFRAGE**************/

  public Cesar(String t, int d){
    this.texte = t;
    this.distance = d;
  }

/****************CONSTRUCTEUR POUR LE DECRYPTAGE************************/

  public Cesar(String t, String m){
    this.texte = t;
    this.motConnu = m;
  }

  public Cesar(String t, HashSet<String> d){
    this.texte = t;
    this.dictionnaire = d;
  }

  public Cesar(String t){
    this.texte = t;
  }

/*****************SETTEUR POUR LA DISTANCE****************************/

public void setDistance(int d){
  this.distance = d;
}

/*******************Methode chiffrer**********************************/

  public String chiffrer(){

    String texte_chiffrer = "";
    char caractere;
    char caractere_chiffrer;

    for(int i = 0; i < texte.length(); i++){ //parcourt le texte
      caractere = (char)(texte.charAt(i)); //recupere chaque caractere
      if(caractere >= 'a' && caractere <= 'z'){ //verifie si le caractere est entre a et z
        caractere_chiffrer = (char) (caractere + distance); //chiffre le caractere en ajoutant la distance
        if(caractere_chiffrer > 'z'){ //verifie si on a depasse z
          caractere_chiffrer = (char)(caractere - 26 + distance); //si c'est le cas, retire 26 pour replacer le caractere dans l'alphabet
        }

        texte_chiffrer += caractere_chiffrer; //rajoute le caractere chiffre au texte chiffre

      }
    //  if(caractere == ' ') //cas ou le caractere est un espace
      else
        texte_chiffrer += caractere;

    }
    return texte_chiffrer;
  }


  /********************Methode dechiffrer***************************/

  public String dechiffrer(){ //marche de la meme maniere que chiffrer

    String texte_dechiffrer = "";
    char caractere;
    char caractere_dechiffrer;

    for(int i = 0; i < texte.length(); i++){
      caractere = (char)(texte.charAt(i));
      if(caractere >= 'a' && caractere <= 'z'){
        caractere_dechiffrer = (char) (caractere - distance); //retire le decalafe
        if(caractere_dechiffrer < 'a'){ //verifie si on a depasse a
          caractere_dechiffrer = (char)(caractere + 26 - distance); //rajoute 26 pour placer le caractere dans l'alphabet
        }
        texte_dechiffrer += caractere_dechiffrer;
      }
      else
        texte_dechiffrer += caractere;
    }
    return texte_dechiffrer;
  }

/************************methode pour compter le decalage*************/

public int decalageLettre(char a, char b){
  int decalage = Math.abs((int)a - (int)b);
  return decalage;
}

public int decalageLettre(int a, int b){
  int decalage = Math.abs(a - b);
  return decalage;
}


/***********methode pour trouver le decalage en connaissant un mot*********/

public int motConnu(){
  String tabString [] = texte.split("[ |\n]");
  System.out.println("ici");
  String tabMotConnu [] = new String[motConnu.length()];
  int decal = 0;

  int decalage = 0;
  int tailleMotConnu = motConnu.length();

  for(int i = 0; i < tabString.length; i++){
    if(tabString[i].length() == tailleMotConnu){
      System.out.println(tabString[i]);
      decal = decalageLettre(tabString[i].charAt(0), motConnu.charAt(0));
      System.out.println(decal);
        for(int j=0; j<tailleMotConnu; j++){
          System.out.println("decal: "+decal);
          System.out.println("decal2: "+decalageLettre(tabString[i].charAt(j), motConnu.charAt(j)));

          if(!(decal == (decalageLettre(tabString[i].charAt(j), motConnu.charAt(j))))){
          decal = -1;
          break;
        }
        }
        if(decal != -1)
          return decal;
    }
  }
  return decal;
}

/****************methode pour renvoyer l'indice de la valeur max************/

public int maximum(int [] tableau){
  int max = 0;
  int t = 0;
  for(int i = 0; i < tableau.length; i++){
    if(max < tableau[i]){
      max = tableau[i];
      t = i;
    }
  }
  return t;
}

/***************************methode frequenceLettre*******************/

public int frequenceLettre(){
  char frequences[] = {'e','t','a','o','n','i','s','r','h','l','d','c','u','m','f','p','w','g','b','y','v','k','x','j','q','z'};
  int[] compt = new int[26];
  char[] freq = new char[26];
  int lettreMax;
  int decalage;
  String texteDechiffreTemp;
  char caractere;

  for (int i = 0; i < texte.length(); i++) {
    caractere = texte.charAt(i);
    if (caractere >= 'a' && caractere <= 'z')
      compt[caractere - 'a']++;
  }

  lettreMax = maximum(compt) + 97;

  for (int j = 0; j < frequences.length; j++){

    decalage = decalageLettre(frequences[j], lettreMax);
    Cesar codeTemp = new Cesar(texte, decalage);
    texteDechiffreTemp = codeTemp.dechiffrer();
    if (verif(texteDechiffreTemp))
      return decalage;
  }
  return -1;
  }



public int forceBrute(){
  Cesar codeTemp;
  String texteDechiffreTemp;
  for(int i = 1; i < 27; i++){
    codeTemp = new Cesar(texte, i);
    texteDechiffreTemp = codeTemp.dechiffrer();
    if(verif(texteDechiffreTemp))
      return i;
  }
  return -1;
}

public boolean verif(String txt){

  String [] tableauMot = txt.toString().split("[ |\n]");

  int motCorrect = 0;
  int tailleTexte = tableauMot.length;
  for (int i = 0; i < tailleTexte; i++){

    if (dictionnaire.contains(tableauMot[i]))
      motCorrect++;
  }

  double moyenne = (double)motCorrect / (double)tailleTexte;
  if (moyenne >= 0.5)
    return true;

  return false;
}



}
