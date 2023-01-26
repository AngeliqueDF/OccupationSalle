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