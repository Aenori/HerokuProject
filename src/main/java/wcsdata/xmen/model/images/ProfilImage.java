package wcsdata.xmen.model.images;

import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ProfilImage extends AbstractImage {
    private static List<Pair< Integer, Integer >> imageSizes;

    public ProfilImage(String imagePath) {
        super(imagePath);
    }

    public String getImageSmall() {
        return getImage(150, 150);
    }

    public String getImageProfil() {
        return getImage(400, 400);
    }

    protected List< Pair<Integer, Integer> > getImageSizes() {
        if(imageSizes == null) {
            imageSizes = new ArrayList<>();
            imageSizes.add(Pair.of(150, 150));
            imageSizes.add(Pair.of(400, 400));
        }

        return imageSizes;
    }
}
