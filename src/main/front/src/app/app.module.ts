import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';

import { MagnetService } from './services/magnet.service';
import { CommentService } from './services/comment.service';
import { ProposalService } from './services/proposal.service';
import { RequestService } from './services/request.service';
import { UserService } from './services/user.service';

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule
    ],
    providers: [
        CommentService,
        MagnetService,
        ProposalService,
        RequestService,
        UserService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
