Camunda Consulting Examples
===========================

This repository contains examples, code snippets and demo applications build by the Camunda Consulting Team
or contributors. Note: All examples are only tested manually and there is no guarantee that they are actively maintained. If you search for officially maintained examples go to <a href="https://github.com/camunda/camunda-bpm-examples">https://github.com/camunda/camunda-bpm-examples</a>.

<table>
  <tr>
    <th>Type of Example</th>
    <th>Description</th>
  </tr>
  <tr>
    <td><a href="https://github.com/camunda/camunda-consulting/tree/master/showcases">Show Cases</a></td>
    <td>Complete demo applications showing more complex scenarios</td>
  </tr>
  <tr>
    <td><a href="https://github.com/camunda/camunda-consulting/tree/master/snippets">Snippets</a></td>
    <td>Code snippets for particular technical issues</td>
  </tr>
  <tr>
    <td><a href="https://github.com/camunda/camunda-consulting/tree/master/one-time-examples">One-time Examples</a></td>
    <td>Examples created once for a specific event (like a conference or a magazine article). <b>The code is not maintained and might be out-of-date.</b></td>
  </tr>
</table>



Showcases
-----------------------

<table>
  <tr>
    <td>
      <a href="https://github.com/camunda/camunda-consulting/tree/master/showcases/twitter">Review Process for new Tweets</a> <br />
<img src="showcases/twitter/src/main/resources/TwitterDemoProcess.png" width="200" />
    </td>
    <td>
       Key Features showcased:
       <ul>
<li> Complete Process Application </li>
<li> HTML or JSF task forms </li>
<li> Testing with camunda-bpm-assert and PowerMock + Mockito </li>
<li> [todo] Cycle with Collaboration </li>
<li> [todo] Retries and Incident Handling </li>
      </ul>
    </td>
  </tr>
  <tr>
    <td>
      <a href="https://github.com/camunda/camunda-consulting/tree/master/showcases/camel-use-cases">Camel Use Cases</a> <br />
<img src="https://raw.github.com/camunda/camunda-bpm-camel/master/use-cases.png" width="200" />
    </td>
    <td>
       Key Features showcased:
       <ul>
        <li>Features of Camel Component, especially including messaging</li>
      </ul>
    </td>
  </tr>
 <tr>
    <td>
      <a href="https://github.com/camunda/camunda-consulting/tree/master/showcases/underwriting">Insurance Underwriting</a> <br />
<img src="showcases/underwriting/docs/case.png" width="200" />
    </td>
    <td>
       Key Features showcased:
       <ul>
          <li>CMMN</li>
          <li>Case UI in JSF</li>
          <li>Combination of BPMN, CMMN and Rules</li>
      </ul>
    </td>
  </tr>  
 <tr>
    <td>
      <a href="https://github.com/camunda/camunda-consulting/tree/master/showcases/incident-management">Incident Handling</a> <br />
<img src="showcases/incident-management/src/main/resources/incidentManagement.png" width="200" />
    </td>
    <td>
       Key Features showcased:
       <ul>
          <li>Working process from official OMG by example paper</li>
          <li>BPMN Collaboration</li>
      </ul>
    </td>
  </tr>    
</table>



Snippets
-----------------------

### Human Task Management

<table>
  <tr>
    <th>Preview</th>
    <th>Name</th>
    <th>Description</th>
  </tr>
  <tr>
    <td><img src="snippets/subtask-checklist/screenshot.png" width="50" /></td>
    <td><a href="https://github.com/camunda/camunda-consulting/tree/master/snippets/subtask-checklist">Subtask Checklist</a></td>
    <td>BPMN process with sub tasks configured on a User Task using BPMN Extension Elements. The Subtasks are shown in the HTML task form using the Camunda Forms SDK (JavaScript). A User Task can only be completed if all subtasks are completed beforehand.</td>
  </tr>
</table>

### BPMN

<table>
  <tr>
    <th>Preview</th>
    <th>Name</th>
    <th>Description</th>
  </tr>

  <tr>
    <td><img src="snippets/bpmn-adhoc-task/src/main/resources/process.png" width="50" /></td>
    <td><a href="snippets/bpmn-adhoc-task/">Workaround to realize BPMN AdHoc Subpocess</a></td>
    <td>The BPMN AdHoc Subprocess is not specified to be executable, hence camunda BPM cannot execute it out-of-the-box. This snippet shows a possile workaround.</td>
  </tr>
  
</table>

### Cockpit Plugins

<table>
  <tr>
    <th>Preview</th>
    <th>Name</th>
    <th>Description</th>
  </tr>

  <tr>
    <td><img src="https://i.vimeocdn.com/video/485755185_640.jpg" width="50"></td>
    <td><a href="http://camunda.org/plugins/">Plugin Store</a></td>
    <td>The Plugin Store lists various available cockpit plugins, including the consulting snippets.</td>
  </tr>
  
</table>
