import Entite.*;
import GestionEmplyees.Caissier;
import GestionEmplyees.Employe;
import GestionEmplyees.Responsable;
import GestionEmplyees.Vendeur;

public class Magasin {
    int identifiant;
    String adresse;
    final int CAPACITE_PR=2;
    final int CAPACITE_PE=20;

    Produit [] tabprod=new Produit[CAPACITE_PR];
    Employe [] tabemp=new Employe[CAPACITE_PE];


    static int comprod=0;
    static int comEmp =0;

    public Magasin(){}
    public Magasin(int id,String ad)
    {
        this.identifiant=id;
        adresse=ad;
    }

    public static int getComprod() {
        return comprod;
    }

    public static int getComEmp() {
        return comEmp;
    }

    public Produit[] getTabprod() {
        return tabprod;
    }

    public void ajouterProduit(Produit produit) throws  PrixNegatifException , MagasinPleinException{
        if (produit.getPrix() < 0) {
            throw new PrixNegatifException("Le prix du produit ne peut pas être négatif.");
        }
        if (comprod >= CAPACITE_PR) {
            throw new MagasinPleinException("Capacité maximale du magasin atteinte.");
        }
        tabprod[comprod] = produit;
        comprod++;
    }


    public void afficher() {
        System.out.println("id : " + identifiant + " adresse : " + adresse);

        System.out.println("Produits dans le magasin :");
        for (int i = 0; i < comprod; i++) {
            if (tabprod[i] != null) {
                tabprod[i].afficher();
            }
        }
        System.out.println("Les employés du magasin :");
        for (int i = 0; i < comEmp; i++) {
            if (tabemp[i] != null) {
                tabemp[i].afficher();
            }
        }
    }

    public boolean chercherProduit(Produit p) {
        for (int i = 0; i < comprod; i++) {
            if (tabprod[i].comparer(p)) {
                return true;
            }
        }
        return false;
    }

    public void supprimerProduit(Produit p) {
        for (int i = 0; i < comprod; i++) {
            if (tabprod[i].comparer(p)) {
                for (int j = i; j < comprod - 1; j++) {
                    tabprod[j] = tabprod[j + 1];
                }
                tabprod[comprod-1]=null;
                comprod--;
                break;
            }
        }
    }
    public Magasin trouveMagasin(Magasin m1, Magasin m2) {
        if (m1.comprod> m2.comprod) {
            return m1;
        } else if (m1.comprod <m2.comprod) {
            return m2;
        } else {
            System.out.println("Deux magasins ont même nombre de produits");
            return null;
        }
    }

    public String ajouterEmploye(Employe e) {

        tabemp[comEmp] = e;
        comEmp++;
        return "Employé ajouté";
    }

    public void afficherSalaire(){
        for (int i=0; i<comEmp;i++){
            if (tabemp[i] != null) {
            System.out.println("Salaire " +tabemp[i].calculersalaire());}
        }
    }

    public void affichePrimeResponsable() {
        for (int i = 0; i < comEmp; i++) {
            if (tabemp[i] instanceof Responsable) {
                Responsable responsable = (Responsable) tabemp[i]; //donwcast
                System.out.println("Prime du Responsable " + responsable.getNom() + ": " + responsable.getPrime());
            }
        }
    }

    public void afficheNombreEmployesParType() {
        int caissiers = 0;
        int vendeurs = 0;
        int responsables = 0;
        for (int i = 0; i < comEmp; i++) {
            if (tabemp[i] instanceof Caissier) {
                caissiers++;
            } else if (tabemp[i] instanceof Vendeur) {
                vendeurs++;
            } else if (tabemp[i] instanceof Responsable) {
                responsables++;
            }
        }
        System.out.println("Nombre de Caissiers: " + caissiers);
        System.out.println("Nombre de Vendeurs: " + vendeurs);
        System.out.println("Nombre de Responsables: " + responsables);
    }




}

