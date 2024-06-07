import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Router, RouterLink } from '@angular/router';
import { SidebarService } from 'src/app/services/component/service-sidebar/sidebar.service';

@Component({
  selector: 'app-sidebar-v1',
  standalone: true,
  imports: [CommonModule, RouterLink, MatIconModule],
  templateUrl: './sidebar-v1.component.html',
  styleUrl: './sidebar-v1.component.scss'
})
export class SidebarV1Component implements OnInit {

  constructor(
    public router: Router,
    private sidebarServ: SidebarService,
  ){}

  ngOnInit(): void {
    this.sidebarServ.closeSidebar();
  }

  public closeSidebar(): void {
    this.sidebarServ.closeSidebar();
  }

  public getCurrentDate(): string {
    return (new Date().toLocaleDateString('es', {
      weekday:"long", year:"numeric", month:"long", day:"numeric"
    }))
  }

  public logout(): void {
    this.router.navigateByUrl('/auth/login');
  }
}
