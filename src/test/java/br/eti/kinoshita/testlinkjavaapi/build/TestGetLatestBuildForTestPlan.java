/*
 * The MIT License
 *
 * Copyright (c) <2010> <Bruno P. Kinoshita>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.eti.kinoshita.testlinkjavaapi.build;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.BaseTest;
import br.eti.kinoshita.testlinkjavaapi.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.model.Build;

/**
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 
 */
public class TestGetLatestBuildForTestPlan 
extends BaseTest
{

	@DataProvider(name="buildData")
	public Object[][] createData()
	{
		return new Object[][] 
        {
			{
				5, 
				7, 
				"tdc-1.0", 
				"Build creado automaticamente con TestLink Jenkins Plug-in."
			}
        };
	}
	
	@Test(dataProvider="buildData")
	public void testGetLatestBuildForTestPlan( Integer testPlanId, Integer buildId, String buildName, String buildNotes )
	{
		this.loadXMLRPCMockData("tl.getLatestBuildForTestPlan.xml");
		Build build = null;
		
		try
		{
			build = api.getLatestBuildForTestPlan( testPlanId );
		}
		catch (TestLinkAPIException e)
		{
			Assert.fail(e.getMessage(), e);
		}
		
		Assert.assertNotNull( build );
		
		Assert.assertTrue( build.getId() > 0 );	
		
		Assert.assertEquals( build.getId(), buildId );
		
		Assert.assertEquals( build.getTestPlanId(), testPlanId );
		
		Assert.assertEquals( build.getName(), buildName );
		
		Assert.assertEquals( build.getNotes(), buildNotes );
	}
	
}
