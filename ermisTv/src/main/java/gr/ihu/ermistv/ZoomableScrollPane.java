/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.ihu.ermistv;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author User
 */

    public class ZoomableScrollPane extends ScrollPane {

        @FXML private ScrollPane extension;



        private double scaleValue = 0.7;
        private double zoomIntensity = 0.02;
        private Node target;
        private Node zoomNode;

        public ZoomableScrollPane(Node target) {//Node target
            super();
            this.target = target;
            this.zoomNode = new Group(target);
            setContent(outerNode(zoomNode));

            setPannable(true);
            setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            setFitToHeight(true); //center
            setFitToWidth(true); //center

            updateScale();
        }
        public ZoomableScrollPane(){
            
        }

        private Node outerNode(Node node) {
            Node outerNode = centeredNode(node);
            outerNode.setOnScroll(e -> {
                e.consume();
                onScroll(e.getTextDeltaY(), new Point2D(e.getX(), e.getY()));
            });
            return outerNode;
        }

        private Node centeredNode(Node node) {
            VBox vBox = new VBox(node);
            vBox.setAlignment(Pos.CENTER);
            return vBox;
        }

        private void updateScale() {
            target.setScaleX(scaleValue);
            target.setScaleY(scaleValue);
        }

        private void onScroll(double wheelDelta, Point2D mousePoint) {
            double zoomFactor = Math.exp(wheelDelta * zoomIntensity);

            Bounds innerBounds = zoomNode.getLayoutBounds();
            Bounds viewportBounds = getViewportBounds();

            // calculate pixel offsets from [0, 1] range
            double valX = this.getHvalue() * (innerBounds.getWidth() - viewportBounds.getWidth());
            double valY = this.getVvalue() * (innerBounds.getHeight() - viewportBounds.getHeight());

            scaleValue = scaleValue * zoomFactor;
            updateScale();
            this.layout(); // refresh ScrollPane scroll positions & target bounds

            // convert target coordinates to zoomTarget coordinates
            Point2D posInZoomTarget = target.parentToLocal(zoomNode.parentToLocal(mousePoint));

            // calculate adjustment of scroll position (pixels)
            Point2D adjustment = target.getLocalToParentTransform().deltaTransform(posInZoomTarget.multiply(zoomFactor - 1));

            // convert back to [0, 1] range
            // (too large/small values are automatically corrected by ScrollPane)
            Bounds updatedInnerBounds = zoomNode.getBoundsInLocal();
            this.setHvalue((valX + adjustment.getX()) / (updatedInnerBounds.getWidth() - viewportBounds.getWidth()));
            this.setVvalue((valY + adjustment.getY()) / (updatedInnerBounds.getHeight() - viewportBounds.getHeight()));
        }
    }
