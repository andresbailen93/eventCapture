/*
 * Copyright (C) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.andres.utils;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;


import java.util.ArrayList;
import java.util.List;

public class AccessibilityServiceCompatUtils {
    static ArrayList<AccessibilityNodeInfoCompat> lisNodes;

    /**
     * @return root node of the Application window
     */
    public static AccessibilityNodeInfoCompat getRootInActiveWindow(AccessibilityService service) {
        if(service == null) return null;

        AccessibilityNodeInfo root = service.getRootInActiveWindow();
        if (root == null) return null;
        return new AccessibilityNodeInfoCompat(root);
    }

    /**
     * @return root node of the window that currently has accessibility focus
     */
    public static AccessibilityNodeInfoCompat getRootInAccessibilityFocusedWindow(
            AccessibilityService service) {
        if (service == null) {
            return null;
        }

        AccessibilityNodeInfo focusedRoot = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            List<AccessibilityWindowInfo> windows = service.getWindows();
            WindowManager manager = new WindowManager();
            manager.setWindows(windows);
            AccessibilityWindowInfo accessibilityFocusedWindow = manager.getCurrentWindow();

            if (accessibilityFocusedWindow != null) {
                focusedRoot = accessibilityFocusedWindow.getRoot();
            }
        }

        if (focusedRoot == null) {
            focusedRoot = service.getRootInActiveWindow();
        }

        if (focusedRoot == null) {
            return null;
        }

        return new AccessibilityNodeInfoCompat(focusedRoot);
    }

    public static void setFocusOnFirstElement(AccessibilityNodeInfoCompat root){
        lisNodes = new ArrayList<>();
        AccessibilityNodeInfoCompat nodeWithFocus;


        for(int i = 0; i < root.getChildCount();i++){
            lisNodes.addAll(loopNode(root.getChild(i)));
        }
        for (AccessibilityNodeInfoCompat nodes : lisNodes) {
            if (nodes.isClickable()){
                nodes.setAccessibilityFocused(true);
            }
        }
    }

    private static List<AccessibilityNodeInfoCompat> loopNode(AccessibilityNodeInfoCompat node){
        ArrayList<AccessibilityNodeInfoCompat> listnode = new ArrayList<>();
        for(int i = 0; i<node.getChildCount(); i++){
            listnode.add(node.getChild(i));
        }
        return listnode;
    }
}
