package lab5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

public class Animation implements ActionListener, KeyListener {
    private TransformGroup cat;
    private Transform3D transform3D = new Transform3D();

    private float x = 0;
    private float z = 0;

    private boolean isMoveForward = false;
    private boolean isMoveBack = false;
    private boolean isMoveLEft = false;
    private boolean isMoveRight = false;
    private boolean isRotateLeft = false;
    private boolean isRotateRight = false;

    Animation(TransformGroup cat) {
        this.cat = cat;
        this.cat.getTransform(this.transform3D);
        Timer timer = new Timer(20, this);
        timer.start();
    }

    private void Move() {
        if (isMoveForward && z > -4) {
            z -= 0.02f;
        }
        if (isMoveBack && z <0.2f ) {
            z += 0.02f;
        }
        if (isMoveLEft && x > -1f) {
            x -= 0.02f;
        }
        if (isMoveRight && x < 1f) {
            x += 0.02f;
        }

        transform3D.setTranslation(new Vector3f(x, 0, z));
        if (isRotateLeft) {
            Transform3D rotation = new Transform3D();
            rotation.rotZ(-0.05f);
            transform3D.mul(rotation);
        }
        if (isRotateRight) {

            Transform3D rotation = new Transform3D();
            rotation.rotZ(+0.05f);
            transform3D.mul(rotation);
        }
        cat.setTransform(transform3D);
    }

    @Override
    public void actionPerformed(ActionEvent isRotateLeft) {
        Move();
    }

    @Override
    public void keyPressed(KeyEvent ev) {
        switch (ev.getKeyChar()) {
            case 'w':
                isMoveForward = true;
                break;
            case 's':
                isMoveBack = true;
                break;
            case 'a':
                isMoveLEft = true;
                break;
            case 'd':
                isMoveRight = true;
                break;
            case 'q':
                isRotateLeft = true;
                break;
            case 'e':
                isRotateRight = true;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent isRotateLeft) {}

    @Override
    public void keyReleased(KeyEvent ev) {
        switch (ev.getKeyChar()) {
            case 'w':
                isMoveForward = false;
                break;
            case 's':
                isMoveBack = false;
                break;
            case 'a':
                isMoveLEft = false;
                break;
            case 'd':
                isMoveRight = false;
                break;
            case 'q':
                isRotateLeft = false;
                break;
            case 'e':
                isRotateRight = false;
                break;
        }
    }
}