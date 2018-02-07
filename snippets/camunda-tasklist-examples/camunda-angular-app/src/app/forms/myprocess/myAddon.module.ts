import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { StartNewProcessComponent } from './startNewProcess.component';

@NgModule({
  entryComponents: [StartNewProcessComponent],
  declarations: [StartNewProcessComponent],
  imports: [FormsModule],
  exports: [StartNewProcessComponent]
})
export class MyAddonModule {}

export { StartNewProcessComponent } from './startNewProcess.component';
