package net.lightglow.common.config;

import blue.endless.jankson.Comment;
import io.wispforest.owo.config.annotation.Config;

@Config(name="skeletalremains_common",wrapperName = "SkeletalConfigSpawn")
public class SkeletalConfig {
        public int overgrownweight = 130;
        public int overgrownminspawn = 1;
        public int overgrownmaxspawn = 2;

        public int sharpshooterweight = 150;
        public int sharpshooterminspawn = 1;
        public int sharpshootermaxspawn = 2;

        public int charredweight = 120;
        public int charredminspawn = 1;
        public int charredmaxspawn = 2;

        public int sunkenweight = 180;
        public int sunkenminspawn = 1;
        public int sunkenmaxspawn = 3;

        public int swampedweight = 100;
        public int swampedminspawn = 1;
        public int swampedmaxspawn = 3;

        public int desertedweight = 80;
        public int desertedminspawn = 1;
        public int desertedmaxspawn = 3;

        // @Override
        // public String getName() {return SkeletalRemains.MOD_ID + "-common";}
}
