// $Id$
/*
 * WorldEdit
 * Copyright (C) 2010 sk89q <http://www.sk89q.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package com.sk89q.worldedit;

import java.util.Iterator;

/**
 *
 * @author Albert
 */
public class CuboidRegion implements Region {
    /**
     * Store the first point.
     */
    private Vector pos1;
    /**
     * Store the second point.
     */
    private Vector pos2;

    /**
     * Construct a new instance of this cuboid region.
     * 
     * @param pos1
     * @param pos2
     */
    public CuboidRegion(Vector pos1, Vector pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    /**
     * Get the lower point of the cuboid.
     *
     * @return min point
     */
    @Override
    public Vector getMinimumPoint() {
        return new Vector(Math.min(pos1.getX(), pos2.getX()),
                         Math.min(pos1.getY(), pos2.getY()),
                         Math.min(pos1.getZ(), pos2.getZ()));
    }

    /**
     * Get the upper point of the cuboid.
     *
     * @return max point
     */
    @Override
    public Vector getMaximumPoint() {
        return new Vector(Math.max(pos1.getX(), pos2.getX()),
                         Math.max(pos1.getY(), pos2.getY()),
                         Math.max(pos1.getZ(), pos2.getZ()));
    }

    /**
     * Get the number of blocks in the region.
     * 
     * @return number of blocks
     */
    public int getSize() {
        Vector min = getMinimumPoint();
        Vector max = getMaximumPoint();

        return (int)((max.getX() - min.getX() + 1) *
                     (max.getY() - min.getY() + 1) *
                     (max.getZ() - min.getZ() + 1));
    }

    /**
     * Get X-size.
     *
     * @return width
     */
    public int getWidth() {
        Vector min = getMinimumPoint();
        Vector max = getMaximumPoint();

        return (int)(max.getX() - min.getX() + 1);
    }

    /**
     * Get Y-size.
     *
     * @return height
     */
    public int getHeight() {
        Vector min = getMinimumPoint();
        Vector max = getMaximumPoint();

        return (int)(max.getY() - min.getY() + 1);
    }

    /**
     * Get Z-size.
     *
     * @return length
     */
    public int getLength() {
        Vector min = getMinimumPoint();
        Vector max = getMaximumPoint();

        return (int)(max.getZ() - min.getZ() + 1);
    }

    /**
     * Expands the cuboid in a direction.
     *
     * @param change
     */
    public void expand(Vector change) {
        if (change.getX() > 0) {
            if (Math.max(pos1.getX(), pos2.getX()) == pos1.getX()) {
                pos1 = pos1.add(new Vector(change.getX(), 0, 0));
            } else {
                pos2 = pos2.add(new Vector(change.getX(), 0, 0));
            }
        } else {
            if (Math.min(pos1.getX(), pos2.getX()) == pos1.getX()) {
                pos1 = pos1.add(new Vector(change.getX(), 0, 0));
            } else {
                pos2 = pos2.add(new Vector(change.getX(), 0, 0));
            }
        }

        if (change.getY() > 0) {
            if (Math.max(pos1.getY(), pos2.getY()) == pos1.getY()) {
                pos1 = pos1.add(new Vector(0, change.getY(), 0));
            } else {
                pos2 = pos2.add(new Vector(0, change.getY(), 0));
            }
        } else {
            if (Math.min(pos1.getY(), pos2.getY()) == pos1.getY()) {
                pos1 = pos1.add(new Vector(0, change.getY(), 0));
            } else {
                pos2 = pos2.add(new Vector(0, change.getY(), 0));
            }
        }

        if (change.getZ() > 0) {
            if (Math.max(pos1.getZ(), pos2.getZ()) == pos1.getZ()) {
                pos1 = pos1.add(new Vector(0, 0, change.getZ()));
            } else {
                pos2 = pos2.add(new Vector(0, 0, change.getZ()));
            }
        } else {
            if (Math.min(pos1.getZ(), pos2.getZ()) == pos1.getZ()) {
                pos1 = pos1.add(new Vector(0, 0, change.getZ()));
            } else {
                pos2 = pos2.add(new Vector(0, 0, change.getZ()));
            }
        }
    }

    /**
     * Contracts the cuboid in a direction.
     *
     * @param change
     */
    public void contract(Vector change) {
        if (change.getX() < 0) {
            if (Math.max(pos1.getX(), pos2.getX()) == pos1.getX()) {
                pos1 = pos1.add(new Vector(change.getX(), 0, 0));
            } else {
                pos2 = pos2.add(new Vector(change.getX(), 0, 0));
            }
        } else {
            if (Math.min(pos1.getX(), pos2.getX()) == pos1.getX()) {
                pos1 = pos1.add(new Vector(change.getX(), 0, 0));
            } else {
                pos2 = pos2.add(new Vector(change.getX(), 0, 0));
            }
        }

        if (change.getY() < 0) {
            if (Math.max(pos1.getY(), pos2.getY()) == pos1.getY()) {
                pos1 = pos1.add(new Vector(0, change.getY(), 0));
            } else {
                pos2 = pos2.add(new Vector(0, change.getY(), 0));
            }
        } else {
            if (Math.min(pos1.getY(), pos2.getY()) == pos1.getY()) {
                pos1 = pos1.add(new Vector(0, change.getY(), 0));
            } else {
                pos2 = pos2.add(new Vector(0, change.getY(), 0));
            }
        }

        if (change.getZ() < 0) {
            if (Math.max(pos1.getZ(), pos2.getZ()) == pos1.getZ()) {
                pos1 = pos1.add(new Vector(0, 0, change.getZ()));
            } else {
                pos2 = pos2.add(new Vector(0, 0, change.getZ()));
            }
        } else {
            if (Math.min(pos1.getZ(), pos2.getZ()) == pos1.getZ()) {
                pos1 = pos1.add(new Vector(0, 0, change.getZ()));
            } else {
                pos2 = pos2.add(new Vector(0, 0, change.getZ()));
            }
        }
    }

    /**
     * Get position 1.
     * 
     * @return position 1
     */
    public Vector getPos1() {
        return pos1;
    }

    /**
     * Set position 1.
     * 
     * @param pos1
     */
    public void setPos1(Vector pos1) {
        this.pos1 = pos1;
    }

    /**
     * Get position 2.
     * 
     * @return position 2
     */
    public Vector getPos2() {
        return pos2;
    }

    /**
     * Set position 2.
     *
     * @param pos2
     */
    public void setPos2(Vector pos2) {
        this.pos2 = pos2;
    }

    /**
     * Get the iterator.
     * 
     * @return iterator of Points
     */
    public Iterator<Vector> iterator() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
