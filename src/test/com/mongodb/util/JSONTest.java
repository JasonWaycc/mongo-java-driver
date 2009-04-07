// JSONTest.java

/**
 *      Copyright (C) 2008 10gen Inc.
 *  
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.mongodb.util;

import org.testng.annotations.Test;

public class JSONTest extends com.mongodb.util.TestCase {

    @org.testng.annotations.Test(groups = {"basic"})
    public void testNumbers(){
        JSON.parse("{'x' : 5. }");
        JSON.parse("{'x' : 5.0 }");
        JSON.parse("{'x' : 0 }");
        JSON.parse("{'x' : 500 }");
        JSON.parse("{'x' : 0.500 }");
    }

    @org.testng.annotations.Test(groups = {"basic"})
    public void testSimple() {
        JSON.parse("{'csdf' : true}");
        JSON.parse("{'csdf' : false}");
        JSON.parse("{'csdf' : null}");
    }

    @org.testng.annotations.Test(groups = {"basic"})
    public void testString() {
        JSON.parse("{'csdf' : \"foo\"}");
        JSON.parse("{'csdf' : \"\\\"\"}");
    }
    
    @org.testng.annotations.Test(groups = {"basic"})
    public void testBasic(){
        assertEquals( JSON.serialize(JSON.parse("{}")), "{ }");
        assertEquals( JSON.parse(""), null );
        assertEquals( JSON.parse("     "), null);
        assertEquals( JSON.parse(null), null);

        boolean threw = false;
        try {
            JSON.parse("{");
        }
        catch(JSONParseException e) {
            threw = true;
        }
        assertEquals(threw, true);
        threw = false;

        try {
            JSON.parse("}");
        }
        catch(JSONParseException e) {
            threw = true;
        }
        assertEquals(threw, true);
        threw = false;

        try {
            JSON.parse("{{}");
        }
        catch(JSONParseException e) {
            threw = true;
        }
        assertEquals(threw, true);
    }



    public static void main( String args[] ){
        (new JSONTest()).runConsole();
    }
    
}
