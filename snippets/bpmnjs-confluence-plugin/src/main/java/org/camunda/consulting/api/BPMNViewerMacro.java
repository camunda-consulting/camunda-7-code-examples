package org.camunda.consulting.api;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.content.render.xhtml.XhtmlException;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.pages.Attachment;
import com.atlassian.confluence.pages.DefaultAttachmentManager;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import com.atlassian.confluence.xhtml.api.MacroDefinition;
import com.atlassian.confluence.xhtml.api.MacroDefinitionHandler;
import com.atlassian.confluence.xhtml.api.XhtmlContent;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Scanned
public class BPMNViewerMacro implements Macro
{
    private final XhtmlContent xhtmlUtils;

    @Autowired
    public BPMNViewerMacro(@ComponentImport XhtmlContent xhtmlUtils)
    {
        this.xhtmlUtils = xhtmlUtils;
    }

    @Override
    public String execute(Map<String, String> parameters, String bodyContent, ConversionContext conversionContext) throws MacroExecutionException
    {
        String body = conversionContext.getEntity().getBodyAsString();
        
        final List<MacroDefinition> macros = new ArrayList<MacroDefinition>();
        
        List<Attachment> attachments = conversionContext.getEntity().getAttachments();
        String downloadPath = "";
        
        for (Attachment attachment: attachments) {
        	if (parameters!=null && parameters.get("name")!=null && attachment!=null && parameters.get("name").equals(attachment.getFileName())) {
        		downloadPath = attachment.getDownloadPath();
        	}
        }
        try
        {
            xhtmlUtils.handleMacroDefinitions(body, conversionContext, new MacroDefinitionHandler()
            {
                @Override
                public void handle(MacroDefinition macroDefinition)
                {
                    macros.add(macroDefinition);
                }
            });
        }
        catch (XhtmlException e)
        {
            throw new MacroExecutionException(e);
        }

        Map<String, Object> context = MacroUtils.defaultVelocityContext();
        context.put("macroid",UUID.randomUUID());
        context.put("name",downloadPath);
        String height = parameters.get("height");
        if (height == null || height.isEmpty()) {
        	height = "500px";
        }
        context.put("height", height);
        return VelocityUtils.getRenderedTemplate("templates/bpmn-viewer.vm", context);
    }

    @Override
    public BodyType getBodyType()
    {
        return BodyType.NONE;
    }

    @Override
    public OutputType getOutputType()
    {
        return OutputType.BLOCK;
    }
}
