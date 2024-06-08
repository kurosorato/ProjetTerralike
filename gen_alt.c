#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void print_xy(float x[],float y[], int taille) {
    for (int i = 0; i < taille; i++) {
        printf("%d, %d\n", (int) floor(x[i]), (int) floor(y[i]));
    }
}



typedef struct {
    double ref_h;
    double alea_h;
    int ref_répétition;
    int alea_répétition;
    double ref_l;
    double alea_l;
} BiomeData;

typedef struct {
    BiomeData pic;
    BiomeData montagne;
    BiomeData plaine;
} Biome;


Biome biome = {
    .pic = {
        .ref_h = 60,
        .alea_h = 50,
        .ref_répétition = 3,
        .alea_répétition = 3,
        .ref_l = 30,
        .alea_l = 20
    },
    .montagne = {
        .ref_h = 80,
        .alea_h = 30,
        .ref_répétition = 1,
        .alea_répétition = 0,
        .ref_l = 100,
        .alea_l = 80
    },
    .plaine = {
        .ref_h = 0,
        .alea_h = 30,
        .ref_répétition = 1,
        .alea_répétition = 1,
        .ref_l = 100,
        .alea_l = 80
    }
};


BiomeData* rand_biome() {
    int choix = rand() % 6;
    if (choix == 0) {
        return &biome.pic;
    } else if (choix == 1) {
        return &biome.montagne;
    } else {
        return &biome.plaine;
    }
}



void sinus(int lg, int *corr, int a, float (*x)[1000], float (*y)[1000]) {
    int T = lg;
    int fe = 1;
    int nb_point = T * fe;

    float f = 0.5 / (float)lg;
    float dist_point = fe / f;

    for (int i = 0; i < nb_point; i++) {
        float time = (float)i / fe;
        float sinusoid = a * sinf(2 * M_PI * time * f);
        (*x)[i+*corr] = time + *corr;
        (*y)[i+*corr] = sinusoid + 10.0;  
    }

    *corr = nb_point + *corr;


}


int main(int argc, char const *argv[])
{
    
    srand(time(NULL));


    float y[1000] = {0};
    float x[1000] = {0};
    int pos_x = 0;
    int acc;

    int corr = 0;    

    while (pos_x != (900)) {

        //une fonction qui va prendre en entrée biome
        //tirer au sort entre les trois biomes
        //(tirer au sort répétition) + répetition
        //ce chiffre sera la taille du tableau mean (=1), std_dev, c

        BiomeData now = *rand_biome();
        int nb_gen = (rand() % (now.alea_répétition+1)) + now.ref_répétition;

        //longueur et hauteur

        int l[nb_gen];
        int h[nb_gen];


        for (int i = 0; i < nb_gen; ++i)
        {
            l[i] = ((double)rand()/RAND_MAX) * now.alea_l + now.ref_h;
            acc = acc + l[i];
            h[i] = ((double)rand()/RAND_MAX) * now.alea_h + now.ref_h;
        }

        if (acc > 1000) {
            sinus(1000-corr,&corr, 30 ,&x,&y);
            print_xy(x,y, 1000);
            return 0;
        } else {            
            for (int i = 0; i < nb_gen; ++i)
            {
                sinus(l[i],&corr, h[i] ,&x,&y);
            }
        }
    }

    return 0;
}