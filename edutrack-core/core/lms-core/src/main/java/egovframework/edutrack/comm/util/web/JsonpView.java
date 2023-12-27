package egovframework.edutrack.comm.util.web;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.spring.web.servlet.view.JsonView;

@SuppressWarnings("unchecked")
public class JsonpView extends JsonView {
	
	public static final String DEFAULT_CONTENT_TYPE = "text/html";
	
	@Override
	public String getContentType() {
		return DEFAULT_CONTENT_TYPE;
	}
			
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType(DEFAULT_CONTENT_TYPE);
        response.setCharacterEncoding("utf-8");
        
        if("GET".equals(request.getMethod().toUpperCase())) {
            @SuppressWarnings("unchecked")
            Map<String, String[]> params = request.getParameterMap();
            
            String name = params.get("callback")[0];
            name = name.replaceAll("&","&amp;");   
            name = name.replaceAll("<","&lt;");   
            name = name.replaceAll("%3c","&lt;");   
            name = name.replaceAll("%3C","&lt;");   
            name = name.replaceAll(">","&gt;");   
            name = name.replaceAll("%3e","&gt;");   
            name = name.replaceAll("%3E","&gt;");   
            name = name.replaceAll("\"","&quot;"); 
            
            if(params.containsKey("callback")) {
            	PrintWriter out = response.getWriter();
            	out.print(name + "(");
//            	out.print(params.get("callback")[0] + "(");
                super.render(model, request, response);
                out.print(");");
            }

            else {
                super.render(model, request, response);
            }
        }

        else {
            super.render(model, request, response);
        }
    }
}