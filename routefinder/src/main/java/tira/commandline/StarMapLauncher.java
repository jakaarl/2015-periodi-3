package tira.commandline;

import java.util.Arrays;

import javax.swing.SwingUtilities;

import tira.input.JsonStarListReader;
import tira.navigation.StarMap;
import tira.visualization.starmap.StarMapFrame;

/**
 * Launcher for a {@link StarMap} visualization. By default, looks for the file
 * {@value #DEFAULT_STARMAP_PATH} (relative to working directory) or the file
 * specified as an argument. Maximum &quot;jump distance&quot; between stars
 * defaults to {@value #DEFAULT_MAP_JUMP_DISTANCE}, but can be set with the
 * system property {@value #MAX_JUMP_DISTANCE_KEY}.
 *
 */
public class StarMapLauncher {
    
    public static final String MAX_JUMP_DISTANCE_KEY = "maxJumpDistance";
    public static final int DEFAULT_MAP_JUMP_DISTANCE = 750;
    public static final String DEFAULT_STARMAP_PATH = "starmap.json";
    
    public static void main(String[] args) {
        String starMapPath;
        if (args.length == 0) {
            starMapPath = DEFAULT_STARMAP_PATH;
        } else if (args.length == 1) {
            starMapPath = args[0];
        } else {
            throw new IllegalArgumentException(
                    "Illegal arguments (0 or 1 arguments expected): " + Arrays.deepToString(args));
        }
        int maxDistance = Integer.valueOf(
                System.getProperty(MAX_JUMP_DISTANCE_KEY, String.valueOf(DEFAULT_MAP_JUMP_DISTANCE)));
        JsonStarListReader reader = new JsonStarListReader();
        final StarMap map = reader.buildStarMap(starMapPath, maxDistance);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StarMapFrame frame = new StarMapFrame(map);
                frame.setVisible(true);
            }
        });
        
    }

}
