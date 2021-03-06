/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uberfire.wbtest.client.panels.maximize;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.util.Layouts;
import org.uberfire.client.workbench.WorkbenchLayout;
import org.uberfire.client.workbench.widgets.listbar.ResizeFlowPanel;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.wbtest.client.api.AbstractTestScreenActivity;

@Dependent
@Named("org.uberfire.wbtest.client.panels.maximize.MaximizeTestScreen")
public class MaximizeTestScreen extends AbstractTestScreenActivity {

    @Inject
    WorkbenchLayout workbenchLayout;
    private Label sizeLabel = new Label("size not initialized");
    private ResizeFlowPanel panel = new ResizeFlowPanel() {
        @Override
        public void onResize() {
            // unfortunately we have to bake in this assumption to get the real size of the screen
            // (when we live inside the scroll panel, we can't be sized to fill the panel's display area).
            // the cast is a failsafe in case the assumption "parent is scroll panel" becomes wrong.
            ScrollPanel parent = (ScrollPanel) getParent();
            sizeLabel.setText(parent.getOffsetWidth() + "x" + parent.getOffsetHeight());
            super.onResize();
        }
    };
    private String id;

    @Inject
    public MaximizeTestScreen(PlaceManager placeManager) {
        super(placeManager);
    }

    @Override
    public void onStartup(PlaceRequest place) {
        super.onStartup(place);

        id = place.getParameter("debugId",
                                "");

        panel.clear();

        panel.getElement().setId("MaximizeTestScreen-" + id);

        sizeLabel.getElement().setId("MaximizeTestScreen-" + id + "-sizeLabel");
        panel.add(sizeLabel);

        TextBox textBox = new TextBox();
        textBox.getElement().setId("MaximizeTestScreen-" + id + "-textBox");
        panel.add(textBox);

        Button dumpLayoutButton = new Button("Dump Layout");
        dumpLayoutButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                System.out.println(Layouts.getContainmentHierarchy(panel));
            }
        });
        panel.add(dumpLayoutButton);

        Button forceResizeButton = new Button("Force resize");
        forceResizeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                workbenchLayout.onResize();
            }
        });
        panel.add(forceResizeButton);
    }

    @Override
    public String getTitle() {
        return "Maximize Test Screen " + id;
    }

    @Override
    public IsWidget getWidget() {
        return panel;
    }
}
