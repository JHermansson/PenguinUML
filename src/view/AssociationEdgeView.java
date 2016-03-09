package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import model.AbstractEdge;

/**
 * The Graphical Representation of a AssociationEdge.
 */
public class AssociationEdgeView extends AbstractEdgeView {
    private AbstractEdge refEdge;
    private AbstractNodeView startNode;
    private AbstractNodeView endNode;
    private Text startMultiplicity;
    private Text endMultiplicity;

    
    public AssociationEdgeView(AbstractEdge edge, AbstractNodeView startNode, AbstractNodeView endNode) {
        super(edge, startNode, endNode);
        this.refEdge = edge;
        this.startNode = startNode;
        this.endNode = endNode;
        this.setStrokeWidth(super.STROKE_WIDTH);
        this.setStroke(Color.BLACK);
        startMultiplicity = new Text(edge.getStartMultiplicity());
        endMultiplicity = new Text(edge.getEndMultiplicity());
        draw();
        setChangeListeners();

    }

    private void draw() {
        AbstractEdge.Direction direction = refEdge.getDirection();

        getChildren().clear();
        getChildren().add(getLine());

        //Draw multiplicity
        Position position = super.getPosition();
        final double OFFSET = 20;
        switch (position) {
            case RIGHT:
                startMultiplicity.setX(getLine().getStartX() + OFFSET);
                startMultiplicity.setY(getLine().getStartY() + OFFSET);
                endMultiplicity.setX(getLine().getEndX() - OFFSET - endMultiplicity.getText().length() -5);
                endMultiplicity.setY(getLine().getEndY() + OFFSET);
                break;
            case LEFT:
                startMultiplicity.setX(getLine().getStartX() - OFFSET - endMultiplicity.getText().length() -5);
                startMultiplicity.setY(getLine().getStartY() + OFFSET);
                endMultiplicity.setX(getLine().getEndX() + OFFSET);
                endMultiplicity.setY(getLine().getEndY() + OFFSET);
                break;
            case ABOVE:
                startMultiplicity.setX(getLine().getStartX() + OFFSET);
                startMultiplicity.setY(getLine().getStartY() - OFFSET);
                endMultiplicity.setX(getLine().getEndX() + OFFSET);
                endMultiplicity.setY(getLine().getEndY() + OFFSET);
                break;
            case BELOW:
                startMultiplicity.setX(getLine().getStartX() + OFFSET);
                startMultiplicity.setY(getLine().getStartY() + OFFSET);
                endMultiplicity.setX(getLine().getEndX() + OFFSET);
                endMultiplicity.setY(getLine().getEndY() - OFFSET);
        }
        startMultiplicity.toFront();
        endMultiplicity.toFront();
        getChildren().add(startMultiplicity);
        getChildren().add(endMultiplicity);

        //Draw arrows.
        switch(direction) {
            case NO_DIRECTION:
                //Do nothing.
                break;
            case START_TO_END:
                this.getChildren().add(drawArrowHead(getStartX(), getStartY(), getEndX(), getEndY()));
                break;
            case END_TO_START:
                this.getChildren().add(drawArrowHead(getEndX(), getEndY(), getStartX(), getStartY()));
                break;
            case BIDIRECTIONAL:
                this.getChildren().add(drawArrowHead(getStartX(), getStartY(), getEndX(), getEndY()));
                this.getChildren().add(drawArrowHead(getEndX(), getEndY(), getStartX(), getStartY()));
                break;
        }
    }

    /**
     * Draws an ArrowHead and returns it in a group.
     * Based on code from http://www.coderanch.com/t/340443/GUI/java/Draw-arrow-head-line
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return Group.
     */
    private Group drawArrowHead(double startX, double startY, double endX, double endY) {
        Group group = new Group();
        double phi = Math.toRadians(40);
        int barb = 20;
        double dy = startY - endY;
        double dx = startX - endX;
        double theta = Math.atan2(dy, dx);
        double x, y, rho = theta + phi;

        for (int j = 0; j < 2; j++) {
            x = startX - barb * Math.cos(rho);
            y = startY - barb * Math.sin(rho);
            Line arrowHeadLine = new Line(startX, startY, x, y);
            arrowHeadLine.setStrokeWidth(super.STROKE_WIDTH);
            group.getChildren().add(arrowHeadLine);
            rho = theta - phi;
        }
        return group;
    }

    private void setChangeListeners() {
        super.getLine().endXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                draw();
            }
        });

        super.getLine().endYProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                draw();
            }
        });

        super.getLine().startXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                draw();
            }
        });

        super.getLine().startYProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                draw();
            }
        });

        refEdge.startMultiplicityProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                startMultiplicity.setText(newValue);
                draw();
            }
        });

        refEdge.endMultiplicityProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                endMultiplicity.setText(newValue);
                draw();
            }
        });

        refEdge.getDirectionProperty().addListener(new ChangeListener<AbstractEdge.Direction>() {
            @Override
            public void changed(ObservableValue<? extends AbstractEdge.Direction> observable,
                                AbstractEdge.Direction oldValue, AbstractEdge.Direction newValue) {
                draw();
            }
        });
    }
}
