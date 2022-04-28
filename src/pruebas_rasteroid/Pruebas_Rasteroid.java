package pruebas_rasteroid;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Pruebas_Rasteroid extends JFrame implements Runnable{

    private ArrayList<GameObject> naves = new ArrayList<GameObject>();
    private Viewer viewer;
    private JButton boton;
    private JTextArea textArea ;
    
    private int anguloFuerza = 62;
    private int potencia = 50;
    private boolean powerPressed = false;

    public Pruebas_Rasteroid() {
        crearInterfaz(this);
        
        naves.add( new GameObject( new DynamicBody() ));
        naves.add( new GameObject( new DynamicBody() ));
        naves.add( new GameObject( new DynamicBody() ));
        naves.add( new GameObject( new DynamicBody() ));
        naves.add( new GameObject( new DynamicBody() ));

        
        new Thread(this).start();
    }
    
    // ------ GAME CONTROLLER ----------
    
    private void updatePositions() {
        for (GameObject nave : naves) {
            
            if (!powerPressed) {
                nave.getDynamicBody().move();
                if( potencia > 0 )potencia-= 0.5 ;
            } else {
                nave.getDynamicBody().move(anguloFuerza, potencia);
                System.out.println("fuego mami");
            }
            
        }
    }
    
    // ---------------------------------
    
    // ------ INTERFAZ ----------

    private void setWindowParameters() {
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    private void crearInterfaz(Pruebas_Rasteroid papi) {
        setWindowParameters();
        
        // botone
        Container pane = papi.getContentPane();
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        boton = new JButton("ENVIA EL ÁNGULO");
        textArea = new JTextArea("ESCRIBE EL ÁNGULO");
        viewer = new Viewer(naves, papi.getWidth()-30, papi.getHeight()-50);
        viewer.addKeyListener(new TAdapter());

        c.gridx = 0;
        c.gridy = 0;
        pane.add(textArea, c);
        
        c.gridx = 1;
        c.gridy = 0;
        pane.add(boton, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridwidth =2;
        c.gridy = 1;
        pane.add(viewer, c);


        Thread thread2 = new Thread(viewer);
        thread2.start();

        papi.setVisible(true);
        

        //Activar boton
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                int angulo = Integer.parseInt(textArea.getText());
                System.out.println("Me has clocado. El ángulo es " +angulo);
                //TODO 
                //paso el angulo a la nave
                 for (int i = 0; i < naves.size(); i++) {
                       naves.get(i).getDynamicBody().setAngle(angulo);
        }
            }
        });
    }

    // --------------------------
    
    public static void main(String[] args) {
        new Pruebas_Rasteroid();
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                updatePositions();
                powerPressed = false;
                sleep(16);
            } catch (InterruptedException ex) {
                System.out.println("El thread ha sufrido un problema");
            }
        }
    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            powerPressed = true;
        }
    }
}
