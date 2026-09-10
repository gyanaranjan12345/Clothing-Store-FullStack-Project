import {
  Component,
  OnInit
} from '@angular/core';

import {
  CommonModule
} from '@angular/common';

import {
  FormsModule
} from '@angular/forms';

import {
  Category
} from '../../../models/category';

import {
  CategoryService
} from '../../../core/services/category.service';

@Component({
  selector: 'app-admin-categories',

  standalone: true,

  imports: [
    CommonModule,
    FormsModule
  ],

  templateUrl:
    './categories.component.html'
})
export class CategoriesComponent
  implements OnInit {

  categories: Category[] = [];

  newCategory: Category = {

    id: 0,

    name: '',

    description: ''
  };

  constructor(
    private categoryService:
      CategoryService
  ) {}

  ngOnInit(): void {

    this.loadCategories();
  }

  loadCategories(): void {

    this.categoryService
      .getCategories()
      .subscribe({

        next: categories => {

          this.categories = categories;
        }
      });
  }

  addCategory(): void {

    this.categoryService
      .createCategory(
        this.newCategory
      )
      .subscribe({

        next: () => {

          alert(
            'Category created successfully'
          );

          this.newCategory = {

            id: 0,

            name: '',

            description: ''
          };

          this.loadCategories();
        }
      });
  }

  deleteCategory(
    id: number
  ): void {

    if (!confirm(
      'Delete this category?'
    )) {

      return;
    }

    this.categoryService
      .deleteCategory(id)
      .subscribe({

        next: () => {

          this.loadCategories();
        }
      });
  }
}