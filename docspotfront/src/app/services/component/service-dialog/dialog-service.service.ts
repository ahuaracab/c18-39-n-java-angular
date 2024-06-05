import { Injectable } from '@angular/core';
import { DialogParams } from 'src/app/models/components/common/dialog.model';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

@Injectable({
  providedIn: 'root'
})
export class DialogServiceService {

  private standardDialogParams: DialogParams = {
    width: 'auto',
    minWidth : '300px',
    height: 'auto',
    minHeight : '200px',
    hasBackdrop: true,
    backdropClass: '',
    disableClose: false,
  };
  
  constructor(
    public dialog: MatDialog
  ) { }

  /* componentes */

  public closeDialog(id: string = 'loadingWindow') {
    this.dialog.getDialogById(id)?.close();
  }

  public closeAllDialogs() {
    this.dialog.closeAll();
  }

  private setParams<T>(id: string, data: T, dialogParams: DialogParams | undefined): MatDialogConfig {
    const params: MatDialogConfig = {
      id: id,
      data: data,
      width: (dialogParams?.width) ? (dialogParams.width) : (this.standardDialogParams.width),
      minWidth: (dialogParams?.minWidth) ?
          (dialogParams.minWidth) : (this.standardDialogParams.minWidth),
      maxWidth: (dialogParams?.maxWidth) ? (dialogParams.maxWidth) : ('none'),
      height: (dialogParams?.height) ? (dialogParams.height) : (this.standardDialogParams.height),
      minHeight: (dialogParams?.minHeight) ?
          (dialogParams.minHeight) : (this.standardDialogParams.minHeight),
      maxHeight: (dialogParams?.maxHeight) ? (dialogParams.maxHeight) : ('none'),
      hasBackdrop: (dialogParams?.hasBackdrop) ?
          (dialogParams.hasBackdrop) : (this.standardDialogParams.hasBackdrop),
      backdropClass: (dialogParams?.backdropClass) ?
          (dialogParams.backdropClass) : (this.standardDialogParams.backdropClass),
      panelClass: (dialogParams?.panelClass) ?
          (dialogParams.panelClass) : (['blocked-dialog']),
      disableClose: (dialogParams?.disableClose) ?
          (dialogParams.disableClose) : (this.standardDialogParams.disableClose),
    };

    return params;
  }
}
