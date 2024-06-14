import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AjoutQuestionComponent } from './GATEWAY/QCM-SERVICE/Question/ajout-question/ajout-question.component';
import { ListQuestionComponent } from './GATEWAY/QCM-SERVICE/Question/list-question/list-question.component';
import { QuestionsByCategoryComponent } from './GATEWAY/QCM-SERVICE/Question/questions-by-category/questions-by-category.component';
import { UpdateQuestionComponent } from './GATEWAY/QCM-SERVICE/Question/update-question/update-question.component';
import { AjoutQcmComponent } from './GATEWAY/QCM-SERVICE/Qcm/ajout-qcm/ajout-qcm.component';
import { QcmServiceService } from './GATEWAY/QCM-SERVICE/qcm-service.service';
import { ListQcmComponent } from './GATEWAY/QCM-SERVICE/Qcm/list-qcm/list-qcm.component';
import { UpdateQcmComponent } from './GATEWAY/QCM-SERVICE/Qcm/update-qcm/update-qcm.component';
import { AjoutQuestionQcmComponent } from './GATEWAY/QCM-SERVICE/Qcm/ajout-question-qcm/ajout-question-qcm.component';
import { AjoutReponseComponent } from './GATEWAY/QCM-SERVICE/Resultat/ajout-reponse/ajout-reponse.component';
import { ListUserComponent } from './GATEWAY/User/list-user/list-user.component';
import { ListFormationComponent } from './GATEWAY/User/list-formation/list-formation.component';
import { ProfileUserComponent } from './GATEWAY/User/profile-user/profile-user.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import {AuthGuard } from './guards/auth.guard';
import { HistoriqueFormationComponent } from './GATEWAY/User/historique-formation/historique-formation.component';
import { QuizColabComponent } from './GATEWAY/User/quiz-colab/quiz-colab.component';
import { FindByIdComponent } from './GATEWAY/QCM-SERVICE/Resultat/find-by-id/find-by-id.component';
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

const routes: Routes = [
  {path : 'AjoutQuestion', component : AjoutQuestionComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'listQuestion', component : ListQuestionComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'questionCat', component : QuestionsByCategoryComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'updatQuestion/:id', component : UpdateQuestionComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'AjoutQuiz', component : AjoutQcmComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'listQuiz', component : ListQcmComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'updateQuiz/:id', component : UpdateQcmComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'AddQustionQcm/:id', component : AjoutQuestionQcmComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'AddResponse/:id', component : AjoutReponseComponent, canActivate : [AuthGuard], data : {roles : ['admin','user','mentor']}},
  {path : 'listUser', component : ListUserComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'ProfileUser', component : ProfileUserComponent, canActivate : [AuthGuard], data : {roles : ['admin','user','mentor']}},
  {path :'sideBar', component : SideBarComponent},
  {path : 'formations', component : ListFormationComponent, canActivate : [AuthGuard], data : {roles : ['admin','user','mentor']}},
  {path : 'listFormations', component: HistoriqueFormationComponent, canActivate : [AuthGuard], data : {roles : ['admin','user','mentor']}},
  {path : 'QuizUser', component : QuizColabComponent, canActivate : [AuthGuard], data : {roles : ['admin','user','mentor']}},
  {path : 'resultat/:id', component : FindByIdComponent, canActivate : [AuthGuard], data : {roles : ['admin','user','mentor']}},
  {path : 'QuizHestorique', component : QuizHestoriqueComponent, canActivate : [AuthGuard], data : {roles : ['admin','user','mentor']}},
  {path : 'addEquipe', component : CreateEquipeComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'Equips', component : EquipesComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'dash', component : DashboardComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : '', component : AceuilComponent},
  {path : 'ajoutCompetence', component : AjoutCompetenceComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'skills', component : SkillListComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'edit-skill/:id', component : SkillUpdateComponent, canActivate : [AuthGuard], data : {roles : ['admin','mentor']}},
  {path : 'user-competence', component : UserCompetenceComponent, canActivate : [AuthGuard], data : {roles : ['admin','user','mentor']}},
  { path: 'unauthorized', component: UnauthorizedComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
