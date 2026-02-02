import { Component } from '@angular/core';

@Component({
  selector: 'app-profile',
  standalone: true,
  template: `
    <div class="profile-container">
      <h2>Perfil de Usuario</h2>
      <p>Contenido del perfil</p>
    </div>
  `,
  styles: [`
    .profile-container {
      padding: 20px;
      max-width: 800px;
      margin: 0 auto;
    }
    
    h2 {
      color: #333;
      margin-bottom: 20px;
    }
  `]
})
export class ProfileComponent {

    
  // Tu lógica del componente aquí
}