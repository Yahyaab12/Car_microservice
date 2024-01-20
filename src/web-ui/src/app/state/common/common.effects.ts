import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { goBack, goRoute, showToast } from './common.action';
import { ToastService } from '../../services/toast.service';
import { LanguageService } from '../../services/language.service';
import { Router } from '@angular/router';
import { tap } from 'rxjs';
import { Location } from '@angular/common';

@Injectable()
export class CommonEffects {

  showToast$ = createEffect(() =>
    this.actions$.pipe(
      ofType(showToast),
      tap(({ messageKey: messageKey, message: message, toastType: toastType }) => {
          if (messageKey) {
            this.toastService.createToast(this.getMessage(messageKey), toastType);
          } else {
            this.toastService.createToast(message as string, toastType);
          }
        }
      )), { dispatch: false });

  goRoute$ = createEffect(() =>
    this.actions$.pipe(
      ofType(goRoute),
      tap(({ routingLink, pathVariables, queryParams }) => {
        let path: string[] = [routingLink]
        if (pathVariables) {
          path.push(...pathVariables);
        }
        this.router.navigate(path, {queryParams: queryParams});
        }
      )), { dispatch: false });

  goToLastLocation$ = createEffect(() =>
    this.actions$.pipe(
      ofType(goBack),
      tap(() =>
        this.location.back()
      )), { dispatch: false });

  constructor(
    private actions$: Actions,
    private router: Router,
    private toastService: ToastService,
    private languageService: LanguageService,
    private location: Location
  ) {}

  private getMessage(messageKey: string): string {
    return this.languageService.getMessage(messageKey);
  }

}
