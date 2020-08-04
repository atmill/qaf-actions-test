/*******************************************************************************
 * Copyright (c) 2019 Infostretch Corporation
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.qmetry.qaf.automation.impl.step.qaf;

import org.testng.Assert;

import com.qmetry.qaf.automation.step.QAFTestStep;

/**
 * @author chirag.jayswal
 *
 */
public class SharedVariableSteps {
	private int i;
	
	@QAFTestStep(description="i have {0} rupees")
	public void i_have_rupees(int i){
		this.i=i;
	}
	
	@QAFTestStep(description="i add {0} rupees")
	public void i_add_rupees(int i){
		this.i=this.i+i;
	}
	
	@QAFTestStep(description="i should have {0} rupees")
	public void i_should_have_rupees(int i){
		Assert.assertEquals(i, this.i);
	}
}
