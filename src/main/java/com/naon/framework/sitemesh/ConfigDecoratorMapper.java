/*
* Naonsoft Inc., Software License, Version 1.0
*
* Copyright (c) 2012 Naonsoft Inc.,
* All rights reserved.
*
* DON'T COPY OR REDISTRIBUTE THIS SOURCE CODE WITHOUT PERMISSION.
* THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
* OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL <<Naonsoft Inc.>> OR ITS
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
* SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
* LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
* USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
* OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
* OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
* SUCH DAMAGE.
*
* For more information on this product, please see
* <<www.naonsoft.com>>
*/
package com.naon.framework.sitemesh; 

import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.AbstractDecoratorMapper;
import com.opensymphony.module.sitemesh.mapper.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *   Sitemesh에서 /scr/m/* 과 같은 경로기반으로 decorator를 사용할 수 있도록 하기 위해서 사용.
 * </pre>
 * <p>변경내용</p>
 * <p>로거를 사용하도록 변경</p>
 * @author 김상현(sanghyun@naonsoft.com)
 * @author 최종성(jhonson@naonsoft.com)
 * @version 1.0
 * @since 1.0 
 *
 */
public class ConfigDecoratorMapper extends AbstractDecoratorMapper {

    Logger logger = LoggerFactory.getLogger(ConfigDecoratorMapper.class);

    private ConfigLoader configLoader = null;

    /** Create new ConfigLoader using '/WEB-INF/decorators.xml' file. */
    public void init(Config config, Properties properties,
            DecoratorMapper parent) throws InstantiationException {
        super.init(config, properties, parent);
        try {
            String fileName = properties.getProperty("config",
                    "/WEB-INF/decorators.xml");
            configLoader = new ConfigLoader(fileName, config);
        } catch (Exception e) {
            throw new InstantiationException(e.toString());
        }
    }

    /**
     * Retrieve {@link com.opensymphony.module.sitemesh.Decorator} based on
     * 'pattern' tag.
     */
    public Decorator getDecorator(HttpServletRequest request, Page page) {
        String thisPath = request.getRequestURI();
        String contextPath = request.getContextPath();
        
        logger.debug("contextPath="+ contextPath);
        
        if (thisPath.startsWith(contextPath)) {
            thisPath = thisPath.substring(contextPath.length());
            logger.debug("thisPath=" + thisPath);
            
        }
        String name = null;
        try {
            name = configLoader.getMappedName(thisPath);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        Decorator result = getNamedDecorator(request, name);
        return result == null ? super.getDecorator(request, page) : result;
    }

    /**
     * Retrieve Decorator named in 'name' attribute. Checks the role if
     * specified.
     */
    public Decorator getNamedDecorator(HttpServletRequest request, String name) {
        Decorator result = null;
        try {
            result = configLoader.getDecoratorByName(name);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        if (result == null
                || (result.getRole() != null && !request.isUserInRole(result
                        .getRole()))) {
            // if the result is null or the user is not in the role
            return super.getNamedDecorator(request, name);
        } else {
            return result;
        }
    }
    
    
}///~
