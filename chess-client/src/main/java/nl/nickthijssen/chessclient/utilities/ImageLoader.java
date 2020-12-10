package nl.nickthijssen.chessclient.utilities;

import javafx.scene.image.Image;

import java.io.File;
import java.util.EnumMap;
import java.util.Map;

public class ImageLoader {

	private static ImageLoader instance;
	private Map<PieceImage, Image> pieceImages;

	private ImageLoader() {
		pieceImages = new EnumMap<>(PieceImage.class);
		loadPieceImages();
	}

	public static synchronized ImageLoader getInstance() {
		if (instance == null) {
			instance = new ImageLoader();
		}
		return instance;
	}

	public Image getImage(PieceImage pieceImage) {
		return pieceImages.get(pieceImage);
	}

	private void loadPieceImages() {
		File[] imageFiles = new File(getClass().getResource("/images/pieces").getPath()).listFiles();

		for (File imageFile : imageFiles) {
			String filePath = imageFile.toURI().toString();
			Image pieceImage = new Image(filePath);
			String enumValue = getFileName(filePath);
			PieceImage type = PieceImage.valueOf(enumValue);
			pieceImages.put(type, pieceImage);
		}
	}

	private String getFileName(String nameWithExtension) {
		if (nameWithExtension == null) {
			return null;
		}

		int folderSlashPosition = nameWithExtension.lastIndexOf('/') + 1;
		int extensionDotPosition = nameWithExtension.lastIndexOf('.');

		if (extensionDotPosition == -1) {
			return nameWithExtension;
		}

		return nameWithExtension.substring(folderSlashPosition, extensionDotPosition);
	}
}
