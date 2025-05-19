import { Routes } from '@angular/router';
import { WebPageComponent } from './web-page/web-page.component';

export const routes: Routes = [
    { path: 'labseq', component: WebPageComponent},
    { path: '', redirectTo: '/labseq', pathMatch: 'full' },
];

