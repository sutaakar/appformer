/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package org.ext.uberfire.social.activities.client.widgets.pagination;

import org.ext.uberfire.social.activities.client.resources.i18n.Constants;
import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.ListItem;
import org.gwtbootstrap3.client.ui.html.Span;
import org.gwtbootstrap3.client.ui.html.Text;

/**
 * PatternFly pager previous link style
 * See more https://www.patternfly.org/widgets/#pagination
 */
public class Previous extends ListItem {

    private final Anchor previousAnchor = new Anchor();
    private final Span previousIcon = new Span();

    public Previous() {
        add(previousAnchor);
        previousIcon.addStyleName("i");
        previousIcon.addStyleName("fa");
        previousIcon.addStyleName("fa-angle-left");
        setText(Constants.INSTANCE.Previous());
        addStyleName("previous");
    }

    public void setText(final String text) {
        previousAnchor.clear();
        previousAnchor.add(previousIcon);
        previousAnchor.add(new Text(text));
    }
}
