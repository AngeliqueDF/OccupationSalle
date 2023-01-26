public class OccupationSalle {
    public static String[] joursSemaine = {"LUNDI", "MARDI", "MERCREDI", "JEUDI", "VENDREDI"};

    public static void main(String[] args) throws InterruptedException {
        boolean[][] occupee = {
                {false, true, true, true, false, true, true, false, true, false},
                {false, true, true, true, false, true, true, false, true, false},
                {false, false, true, true, false, false, true, false, true, false},
                {true, true, false, true, false, true, true, false, true, true},
                {false, true, false, true, false, true, true, false, false, false}
        };
        affichageCreneaux(occupee);
        affichageTauxOccupation(occupee);
        try {
            reserverCreneau(occupee);
        } catch (ReservationCreneauOccupeException re) {
            System.out.println(re.getMessage());
        }
    }

    public static void reserverCreneau(boolean[][] creneaux) throws InterruptedException {
        System.out.println("Entrez l'identifiant à "
                + (chiffresPossibles(creneaux.length) + chiffresPossibles(creneaux[0].length))
                + " chiffres du créneau à réserver (voir ci-dessous) :");
        Thread.sleep(3000);
        for (int i = 0; i < creneaux.length; i++) {
            System.out.println(joursSemaine[i]);
            for (int j = 0; j < creneaux[0].length; j++) {
                if (!creneaux[i][j])
                    System.out.println("\t" + i + j + " - " + afficherHoraire(i, j));
            }
            System.out.println();
        }
        String identifiant = Terminal.lireString();
        int indexJour = Integer.parseInt(String.valueOf(identifiant.charAt(0)));
        int indexCreneau = Integer.parseInt(String.valueOf(identifiant.charAt(1)));
        if (creneaux[indexJour][indexCreneau])
            throw new ReservationCreneauOccupeException();
        creneaux[indexJour][indexCreneau] = true;
        System.out.println("Le créneau du " + afficherHoraire(indexJour, indexCreneau) + " a été réservé.");
    }

    public static int chiffresPossibles(int longueurTableau) {
        int res = 1;
        int ordreGrandeur = 10;
        while (longueurTableau - 1 >= ordreGrandeur) {
            res++;
            ordreGrandeur *= 10;
        }
        return res;
    }

    public static void affichageTauxOccupation(boolean[][] occupee) {
        System.out.println("Taux d'occupation : " + (calculerTauxOccupation(occupee) * 100));
        System.out.println();
    }

    public static float calculerTauxOccupation(boolean[][] creneaux) {
        int creneauxOccupes = 0;
        int creneauxTotaux = creneaux[0].length * creneaux.length;
        for (int i = 0; i < creneaux.length; i++) {
            for (int j = 0; j < creneaux[0].length; j++) {
                if (creneaux[i][j])
                    creneauxOccupes++;
            }
        }
        return (float) creneauxOccupes / creneauxTotaux;
    }

    public static void affichageCreneaux(boolean[][] creneaux) {
        System.out.println("Affichage des crénaux de la semaine :");
        for (int i = 0; i < creneaux.length; i++) {
            System.out.println(joursSemaine[i]);
            for (int j = 0; j < creneaux[0].length; j++) {
                System.out.println("\t" + ecrireInfosCreneau(creneaux[i][j], i, j));
            }
            System.out.println();
        }
    }

    public static String ecrireInfosCreneau(boolean occupee, int indexJour, int indexCreneau) {
        String res = "";
        if (occupee)
            res += "[x] - Salle occupee ";
        else {
            res += "[ ] - Salle libre ";
        }
        res += "de " + afficherHoraire(indexJour, indexCreneau);
        return res;
    }

    public static String afficherHoraire(int indexJour, int indexCreneau) {
        return (8 + indexCreneau) + "H00 à "
                + (8 + indexCreneau + 1) + "H00.";
    }
}