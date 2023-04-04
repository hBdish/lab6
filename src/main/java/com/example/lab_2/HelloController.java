package com.example.lab_2;

import com.example.lab_2.model.MyCircle;
import com.example.lab_2.model.MyRectangle;
import com.example.lab_2.model.MyShape;
import com.example.lab_2.model.ShapeFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.HashMap;


public class HelloController {
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Label label;
    @FXML
    private Label label1;

    @FXML
    private TextField value1;
    private double x, y;

    private double distance_X;
    private double  distance_Y;
    private Boolean flag_rect;
    private Boolean flag_circle;

    @FXML
    protected  void drawButton() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.beginPath();
        gc.setFill(Color.BLUE);
        gc.moveTo(50, 50);
        gc.lineTo(25, 75);
        gc.lineTo(25, 110);
        gc.lineTo(75, 110);
        gc.lineTo(75, 75);
        gc.setFill(Color.RED);
        gc.moveTo(150, 50);
        gc.lineTo(125, 75);
        gc.lineTo(125, 110);
        gc.lineTo(175, 110);
        gc.lineTo(175, 75);
        gc.moveTo(150, 150);
        gc.lineTo(125, 175);
        gc.lineTo(125, 210);
        gc.lineTo(175, 210);
        gc.lineTo(175, 175);
        gc.fill();
        gc.closePath();
    }

    public void drawRectangle(ActionEvent actionEvent) {
        flag_circle =false;
     flag_rect = true;
    }


    public void drawCircle(ActionEvent actionEvent) {
        flag_rect = false;
        flag_circle = true;
    }

    public void draw_circle()
    {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        MyCircle myCircle = new MyCircle(colorPicker.getValue(), x, y, distance_X, distance_Y);
        myCircle.draw(graphicsContext);
    }
    public void draw_rect()
    {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        MyRectangle myRectangle = new MyRectangle(colorPicker.getValue(), x, y, distance_X, distance_Y);
        myRectangle.draw(graphicsContext);
    }
    public void drawTriangle(ActionEvent actionEvent) {
    }

    public void showCoordinate(MouseEvent mouseEvent) {
        String x_str = String.valueOf(mouseEvent.getX());
        x = mouseEvent.getX();
        y = mouseEvent.getY();
        String y_str = String.valueOf(mouseEvent.getY());
        label.setText("X= "+x_str+" "+"Y= "+y_str);
    }

    public void calculateSize(MouseEvent mouseEvent) {
        double new_x = mouseEvent.getX();
        double new_y = mouseEvent.getY();
        distance_X = new_x - x;
        distance_Y = new_y - y;
        String x_str = String.valueOf(mouseEvent.getY());
        String y_str = String.valueOf(mouseEvent.getY());
        label1.setText("X= "+x_str+" "+"Y= "+ y_str);
        if(flag_rect)
        {
         draw_rect();
        }
        else
        {
            if(flag_circle)
            {
                draw_circle();

            }
            else
            {
                label1.setText("Выбери фигуру!");
            }
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public void drawShape(ActionEvent actionEvent) {
        ShapeFactory shapeFactory = new ShapeFactory();
        GraphicsContext gr = canvas.getGraphicsContext2D();

        HashMap<String, Integer> nameAndNames = new HashMap<>();
        nameAndNames.put( "Линия", 0);
        nameAndNames.put( "Угол",1);
        nameAndNames.put( "Треугольник",2);
        nameAndNames.put( "Квадрат",3);
        nameAndNames.put( "Круг",4);
        nameAndNames.put( "Многоугольгик",5);


        int numberOfSides;
        String str = value1.getText();
//        if (isNumeric(str))
//            numberOfSides = Integer.parseInt(str);
//        else
//            numberOfSides = nameAndNames.get(str);

        try {
            numberOfSides = Integer.parseInt(str);

        } catch (NumberFormatException e) {
            numberOfSides = nameAndNames.get(str);
        }

        MyShape shape1 = shapeFactory.createShape(numberOfSides);
        gr.clearRect(0, 0, 800, 600);
        shape1.draw(gr);
        }
    }
