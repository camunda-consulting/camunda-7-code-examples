import * as AT from '../../constants/ActionTypes'
import { CALL_API, Schemas } from '../../middleware/api'

export const postProcessXML = (filename, file) => {
  let body = new FormData()
  var blob = new Blob([file], { type: "text/xml"});
  body.append('data', blob, filename);
  return {
    [CALL_API]: {
      types: [ AT.PROCESS_DEPLOYMENT_REQUEST, AT.PROCESS_DEPLOYMENT_SUCCESS, AT.PROCESS_DEPLOYMENT_FAILURE ],
      endpoint: `deployment/create`,
      schema: Schemas.PROCESS_DEPLOYMENT,
      settings: {
        method: 'post',
        body: body,
        headers: {
          'Accept': 'application/json'
        }
      }
    }
  }
}
