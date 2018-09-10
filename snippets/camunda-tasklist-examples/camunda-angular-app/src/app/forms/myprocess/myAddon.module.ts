import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { startNewProcessComponent } from './startNewProcess.component';
import { approveDataTaskComponent } from './approveDataTask.component';


@NgModule({
  entryComponents: [startNewProcessComponent,approveDataTaskComponent],
  declarations: [startNewProcessComponent,approveDataTaskComponent],
  imports: [FormsModule],
  exports: [startNewProcessComponent,approveDataTaskComponent]
})
export class MyAddonModule {}

export { startNewProcessComponent } from './startNewProcess.component';
export { approveDataTaskComponent } from './approveDataTask.component';
