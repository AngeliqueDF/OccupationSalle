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