import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { SidebarParameters } from 'src/app/models/components/common/sidebarParameter.model';
import { HeaderV1Component } from '../components/header-v1/header-v1.component';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';
import { SidebarService } from 'src/app/services/component/service-sidebar/sidebar.service';
import { SidebarV1Component } from '../components/sidebar-v1/sidebar-v1.component';

@Component({
  selector: 'app-layout-v1',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, MatIconModule, MatSidenavModule, HeaderV1Component],
  templateUrl: './layout-v1.component.html',
  styleUrl: './layout-v1.component.scss',
})
export class LayoutV1Component implements OnInit {
  public sidebarSwitch: boolean = false;
  public sidebarContent: any = null;
  public sidebarParams: SidebarParameters = {
    position: 'end',
    mode: 'over',
    disableClose: false,
  };

  constructor(
    private sidebarServ: SidebarService,
  ) { }

  ngOnInit(): void {
    this.initSidebar();
  }

  private initSidebar() {
    this.sidebarServ.sidebarSwitch$.subscribe((data: boolean) => {
      this.sidebarSwitch = data;
    });
    this.sidebarServ.sidebarContent$.subscribe((content: boolean) => {
      this.sidebarContent = content;
    });
    this.sidebarServ.sidebarParams$.subscribe((params: SidebarParameters) => {
      this.sidebarParams = {
        position: params.position,
        mode: params.mode,
        disableClose: params.disableClose,
      };
    });
  }

  public openMainSidebar() {
    this.sidebarServ.setSidebarContent(SidebarV1Component);
    this.sidebarServ.setSidebarParams('start');
    this.sidebarServ.openSidebar();
  }
}
