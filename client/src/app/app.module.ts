import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { SkillListComponent } from './skill-list/skill-list.component';
import { MatIconModule } from '@angular/material/icon';
import { CharacterHeaderComponent } from './character-header/character-header.component';

@NgModule({
  declarations: [
    AppComponent,
    SkillListComponent,
    CharacterHeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
