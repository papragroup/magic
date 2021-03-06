import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import category, {
  CategoryState
} from 'app/entities/category/category.reducer';
// prettier-ignore
import subCategory, {
  SubCategoryState
} from 'app/entities/sub-category/sub-category.reducer';
// prettier-ignore
import action, {
  ActionState
} from 'app/entities/action/action.reducer';
// prettier-ignore
import bookMarkAction, {
  BookMarkActionState
} from 'app/entities/book-mark-action/book-mark-action.reducer';
// prettier-ignore
import practice, {
  PracticeState
} from 'app/entities/practice/practice.reducer';
// prettier-ignore
import practiceSession, {
  PracticeSessionState
} from 'app/entities/practice-session/practice-session.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly category: CategoryState;
  readonly subCategory: SubCategoryState;
  readonly action: ActionState;
  readonly bookMarkAction: BookMarkActionState;
  readonly practice: PracticeState;
  readonly practiceSession: PracticeSessionState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  category,
  subCategory,
  action,
  bookMarkAction,
  practice,
  practiceSession,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
