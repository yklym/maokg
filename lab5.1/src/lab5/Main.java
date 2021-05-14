package lab5;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import java.awt.Color;
import javax.media.j3d.*;
import javax.media.j3d.Material;
import javax.vecmath.*;
import javax.media.j3d.Background;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.loaders.lw3d.Lw3dLoader;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import javax.swing.Timer;
import javax.swing.JFrame;

public class Main extends JFrame {
    private static Canvas3D canvas;
    private static SimpleUniverse universe;
    private static BranchGroup root;

    private static TransformGroup cat;
    private static int TextureFlags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

    public Main() throws IOException {
        configureWindow();
        configureCanvas();
        configureUniverse();
        root = new BranchGroup();
        addImageBackground();
        addDirectionalLightToUniverse();
        addAmbientLightToUniverse();
        cat = getCat();
        root.addChild(cat);
        root.compile();
        universe.addBranchGraph(root);
    }


    private void configureWindow() {
        setTitle("Lab5");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configureCanvas() {
        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setDoubleBufferEnable(true);
        getContentPane().add(canvas, BorderLayout.CENTER);
    }

    private void configureUniverse() {
        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
    }

    private void addImageBackground() {
        TextureLoader t = new TextureLoader("D:/projects/maokg/lab5.1/assets/back.png", canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
    }

    private void addDirectionalLightToUniverse() {
        BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(100);
        DirectionalLight light = new DirectionalLight(new Color3f(1, 1, 1), new Vector3f(-1, -1, -1));
        light.setInfluencingBounds(bounds);
        root.addChild(light);
    }

    private void addAmbientLightToUniverse() {
        AmbientLight light = new AmbientLight(new Color3f(1, 1, 1));
        light.setInfluencingBounds(new BoundingSphere());
        root.addChild(light);
    }

    private void addAppearance(Shape3D shape, String path) {
        TextureLoader loader = new TextureLoader(path, "RGP", new Container());
        Texture texture = loader.getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));

        TextureAttributes attrs = new TextureAttributes();
        attrs.setTextureMode(TextureAttributes.MODULATE);

        Appearance appearance = new Appearance();
        appearance.setTexture(texture);
        appearance.setTextureAttributes(attrs);

        shape.setAppearance(appearance);
    }

    private TransformGroup getCat() throws IOException {
        Shape3D shape = getModelShape3D("cat", "D:/projects/maokg/lab5.1/assets/cat.obj");

        addAppearance(shape, "D:/projects/maokg/lab5.1/assets/texture.jpg");
        Transform3D transform3D = new Transform3D();
        Transform3D rotY = new Transform3D();
        Transform3D rotX = new Transform3D();
        Transform3D rotZ = new Transform3D();

        rotY.rotY(Math.PI / 2);
        rotX.rotX(-Math.PI / 2);
        rotZ.rotZ(-Math.PI / 4);


        transform3D.setScale(new Vector3d(0.4, 0.4, 0.4));
        transform3D.mul(rotY);
        transform3D.mul(rotX);
        transform3D.mul(rotZ);

        TransformGroup group = getModelGroup(shape);
        group.setTransform(transform3D);

        return group;
    }

    private TransformGroup getModelGroup(Shape3D shape) {
        TransformGroup group = new TransformGroup();
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        group.addChild(shape);
        return group;
    }

    private Shape3D getModelShape3D(String name, String path) throws IOException {
        Scene scene = getSceneFromFile(path);
        Map<String, Shape3D> map = scene.getNamedObjects();
        Shape3D shape = map.get(name);
        scene.getSceneGroup().removeChild(shape);
        return shape;
    }

    private Scene getSceneFromFile(String path) throws IOException {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        file.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
        return file.load(new FileReader(path));
    }

    public static void main(String[] args) {
        try {
            Main window = new Main();
            Animation catMovement = new Animation(cat);
            canvas.addKeyListener(catMovement);
            window.setVisible(true);
        } catch (IOException isRotateLeft) {
            System.out.println(isRotateLeft.getMessage());
        }
    }
}