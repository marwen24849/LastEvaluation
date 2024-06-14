import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AjoutQuestionComponent } from './GATEWAY/QCM-SERVICE/Question/ajout-question/ajout-question.component';
import { SupprimerQuestionComponent } from './GATEWAY/QCM-SERVICE/Question/supprimer-question/supprimer-question.component';
import { UpdateQuestionComponent } from './GATEWAY/QCM-SERVICE/Question/update-question/update-question.component';
import { ListQuestionComponent } from './GATEWAY/QCM-SERVICE/Question/list-question/list-question.component';
import { QuestionsByCategoryComponent } from './GATEWAY/QCM-SERVICE/Question/questions-by-category/questions-by-category.component';
import { QuestionByIdComponent } from './GATEWAY/QCM-SERVICE/Question/question-by-id/question-by-id.component';
import { FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AjoutQcmComponent } from './GATEWAY/QCM-SERVICE/Qcm/ajout-qcm/ajout-qcm.component';
import { UpdateQcmComponent } from './GATEWAY/QCM-SERVICE/Qcm/update-qcm/update-qcm.component';
import { ListQcmComponent } from './GATEWAY/QCM-SERVICE/Qcm/list-qcm/list-qcm.component';
import { GetQcmIdComponent } from './GATEWAY/QCM-SERVICE/Qcm/get-qcm-id/get-qcm-id.component';
import { AjoutQuestionQcmComponent } from './GATEWAY/QCM-SERVICE/Qcm/ajout-question-qcm/ajout-question-qcm.component';
import { AjoutReponseComponent } from './GATEWAY/QCM-SERVICE/Resultat/ajout-reponse/ajout-reponse.component';
import { ListResultatComponent } from './GATEWAY/QCM-SERVICE/Resultat/list-resultat/list-resultat.component';
import { FindByIdComponent } from './GATEWAY/QCM-SERVICE/Resultat/find-by-id/find-by-id.component';
import { UpdateResponseComponent } from './GATEWAY/QCM-SERVICE/Resultat/update-response/update-response.component';
import { ListUserComponent } from './GATEWAY/User/list-user/list-user.component';
import { ProfileUserComponent } from './GATEWAY/User/profile-user/profile-user.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { ListFormationComponent } from './GATEWAY/User/list-formation/list-formation.component';
import { HistoriqueFormationComponent } from './GATEWAY/User/historique-formation/historique-formation.component';
import { QuizColabComponent } from './GATEWAY/User/quiz-colab/quiz-colab.component';
import { QuizHestoriqueComponent } from './GATEWAY/User/quiz-hestorique/quiz-hestorique.component';
import { CreateEquipeComponent } from './GATEWAY/Equipe/create-equipe/create-equipe.component';
import { EquipesComponent } from './GATEWAY/Equipe/equipes/equipes.component';
import { DashboardComponent } from './monitoring/dashboard/dashboard.component';
import { AceuilComponent } from './Aceuil/aceuil/aceuil.component';
import { AjoutCompetenceComponent } from './GATEWAY/Competence/ajout-competence/ajout-competence.component';
import { SkillListComponent } from './GATEWAY/Competence/skill-list/skill-list.component';
import { SkillUpdateComponent } from './GATEWAY/Competence/skill-update/skill-update.component';
import { UserCompetenceComponent } from './GATEWAY/Competence/user-competence/user-competence.component';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';



function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8081',
        realm: 'Evaluation',
        clientId: 'Evaluation-Front'
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }
    });
}

@NgModule({
  declarations: [
    AppComponent,
    AjoutQuestionComponent,
    SupprimerQuestionComponent,
    UpdateQuestionComponent,
    ListQuestionComponent,
    QuestionsByCategoryComponent,
    QuestionByIdComponent,
    AjoutQcmComponent,
    UpdateQcmComponent,
    ListQcmComponent,
    GetQcmIdComponent,
    AjoutQuestionQcmComponent,
    AjoutReponseComponent,
    ListResultatComponent,
    FindByIdComponent,
    UpdateResponseComponent,
    ListUserComponent,
    ProfileUserComponent,
    SideBarComponent,
    ListFormationComponent,
    HistoriqueFormationComponent,
    QuizColabComponent,
    QuizHestoriqueComponent,
    CreateEquipeComponent,
    EquipesComponent,
    DashboardComponent,
    AceuilComponent,
    AjoutCompetenceComponent,
    SkillListComponent,
    SkillUpdateComponent,
    UserCompetenceComponent,
    UnauthorizedComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    KeycloakAngularModule,

  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
